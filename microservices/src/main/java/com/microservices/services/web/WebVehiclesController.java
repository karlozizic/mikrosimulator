package com.microservices.services.web;

import com.microservices.vehicles.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class WebVehiclesController {

    @Autowired
    protected WebVehiclesService vehiclesService;

    protected Logger logger = Logger.getLogger(WebVehiclesController.class.getName());

    public WebVehiclesController (WebVehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "searchText");
    }

    @RequestMapping("/vehicles")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/vehicles/{id}")
    public String byId(Model model, @PathVariable("id") String id) {

        logger.info("web-service byId() invoked: " + id);

        Long vehicleId = Long.valueOf(id);

        Vehicle vehicle = vehiclesService.findById(vehicleId);

        if (vehicle == null) { // no such vehicle
            model.addAttribute("id", vehicleId);
            return "vehicle";
        }

        logger.info("web-service byId() found: " + vehicle);
        model.addAttribute("vehicle", vehicle);
        return "vehicle";

    }

    @RequestMapping(value = "vehicles/search, method = RequestMethod.GET")
    public String searchForm(Model model) {
        model.addAttribute("searchCriteria", new SearchCriteria());
        return "vehicleSearch";
    }

    @RequestMapping(value = "/vehicles/dosearch")
    public String doSearch(Model model, SearchCriteria criteria, BindingResult result) {
        logger.info("web-service search() invoked: " + criteria);

        String id = criteria.getId();
        return byId(model, id);
    }

}
