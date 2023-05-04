package com.microservices.services.web;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class WebVehiclesService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(WebVehiclesService.class.getName());

    public WebVehiclesService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    @PostConstruct
    public void demoOnly() {
        logger.warning("The RestTemplate request factory is " + restTemplate.getRequestFactory().getClass());
    }

    public Vehicle findById(Long id) {

        logger.info("findById() invoked: for " + id);
        logger.info("findById() serviceUrl: " + serviceUrl);
        try {
            return restTemplate.getForObject(serviceUrl + "/vehicles/{id}", Vehicle.class, id);
        } catch (Exception e) {
            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
            return null;
        }

    }

    public List<Vehicle> findAll() {
        logger.info("findAll() invoked");
        Vehicle[] vehicles = null;

        try {
            vehicles = restTemplate.getForObject(serviceUrl + "/vehicles/all", Vehicle[].class, (Object) null);
        } catch (HttpClientErrorException e) {
            // 404 Not Found
        }

        if(vehicles == null || vehicles.length == 0) return null;
        else return Arrays.asList(vehicles);

    }

}
