package com.microservices.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class PaymentController {

    @Autowired
    protected PaymentService paymentService;

    protected Logger logger = Logger.getLogger(PaymentController.class.getName());

    public PaymentController(PaymentService paymentService) {
        logger.info("PaymentController() invoked");
        this.paymentService = paymentService;
    }


    @RequestMapping(path = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vehicle> all() {
        logger.info("payment-service all() invoked");
        List<Vehicle> vehicles = paymentService.findAllVehicles();
        logger.info("payment-service all() found: " + vehicles.size() + " vehicles");
        return vehicles;
    }

}
