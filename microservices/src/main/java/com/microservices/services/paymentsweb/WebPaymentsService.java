package com.microservices.services.paymentsweb;

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
public class WebPaymentsService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(WebPaymentsService.class.getName());

    public WebPaymentsService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    @PostConstruct
    public void demoOnly() {
        logger.warning("The RestTemplate request factory is " + restTemplate.getRequestFactory().getClass());
    }

    public Payment findById(Long id) {

        logger.info("findById() invoked: for " + id);
        logger.info("findById() serviceUrl: " + serviceUrl);
        try {
            return restTemplate.getForObject(serviceUrl + "/payments/{id}", Payment.class, id);
        } catch (Exception e) {
            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
            return null;
        }

    }

    public List<Payment> findAll() {
        logger.info("findAll() invoked");
        Payment[] payments = null;

        try {
            payments = restTemplate.getForObject(serviceUrl + "/payments", Payment[].class, (Object) null);
        } catch (HttpClientErrorException e) {
            // 404 Not Found
        }

        if(payments == null || payments.length == 0) return null;
        else return Arrays.asList(payments);

    }

}