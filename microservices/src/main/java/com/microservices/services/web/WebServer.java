package com.microservices.services.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, //
        DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class WebServer {

    public static final String VEHICLE_SERVICE_URL = "http://VEHICLE-SERVICE";

    public static void main(String[] args) {

        if (System.getProperty("registration.server.hostname") == null)
            System.setProperty("registration.server.hostname", "localhost");

        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(WebServer.class, args);

    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebVehiclesService vehiclesService() {
        return new WebVehiclesService(VEHICLE_SERVICE_URL);
    }

    @Bean
    public WebVehiclesController vehiclesController() {
        return new WebVehiclesController(vehiclesService());
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }


}
