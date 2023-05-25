package com.microservices.payments;

import com.microservices.payments.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PaymentService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String url = "http://VEHICLES-SERVICE";
    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(PaymentService.class.getName());

    public PaymentService() {

        this.serviceUrl = url.startsWith("http") ? url : "http://" + url;

    }

    public List<Vehicle> findAllVehicles() {
        logger.info("findAll() invoked");
        Vehicle[] vehicles = null;

        try {
            logger.info("serviceUrl: " + this.serviceUrl); // serviceUrl je null jer se insancirao kroz drugi constructor
            vehicles = restTemplate.getForObject(this.serviceUrl + "/vehicles/all", Vehicle[].class, (Object) null);
        } catch (HttpClientErrorException e) {
            // 404 Not Found
            logger.info(e.getMessage());
        }

        if(vehicles == null || vehicles.length == 0) return null;
        else return Arrays.asList(vehicles);
    }
}
