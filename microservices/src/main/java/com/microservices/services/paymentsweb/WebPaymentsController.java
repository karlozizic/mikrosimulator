package com.microservices.services.paymentsweb;

import com.microservices.services.web.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class WebPaymentsController {

    @Autowired
    protected WebPaymentsService paymentsService;

    protected Logger logger = Logger.getLogger(WebPaymentsController.class.getName());

    public WebPaymentsController (WebPaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "searchText");
    }

    @RequestMapping("/payments")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/payments/{id}")
    public String byId(Model model, @PathVariable("id") String id) {

        logger.info("payments-service byId() invoked: " + id);

        Long paymentId = Long.valueOf(id);

        Payment payment = paymentsService.findById(paymentId);

        if (payment == null) { // no such vehicle
            model.addAttribute("id", paymentId);
            return "payment";
        }

        logger.info("paymentsweb-service byId() found: " + payment);
        model.addAttribute("payment", payment);
        return "payment";
    }

    @RequestMapping("/payments/all")
    public String all(Model model) {
        logger.info("paymentsweb-service all() invoked");
        List<Payment> payments = paymentsService.findAll();
        logger.info("paymentsweb-service all() found: " + payments.size() + " payments");

        if (payments != null) {
            model.addAttribute("payments", payments);
        }
        return "payments";
    }

    @RequestMapping(value = "payments/search, method = RequestMethod.GET")
    public String searchForm(Model model) {
        model.addAttribute("searchCriteria", new SearchCriteria());
        return "paymentSearch";
    }

    @RequestMapping(value = "/payments/dosearch")
    public String doSearch(Model model, SearchCriteria criteria, BindingResult result) {
        logger.info("paymentsweb-service search() invoked: " + criteria);

        String id = criteria.getId();
        return byId(model, id);
    }

}

