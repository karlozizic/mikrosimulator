package com.microservices.services.paymentsweb;


import com.microservices.services.web.HomeController;
import com.microservices.services.web.WebVehiclesController;
import com.microservices.services.web.WebVehiclesService;
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
public class PaymentswebServer {

    public static final String PAYMENTS_SERVICE_URL = "http://PAYMENTS-SERVICE";

    public static void main(String[] args) {

        if (System.getProperty("registration.server.hostname") == null)
            System.setProperty("registration.server.hostname", "localhost");

        System.setProperty("spring.config.name", "paymentsweb-server");
        SpringApplication.run(PaymentswebServer.class, args);

    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebPaymentsService paymentsService() {
        return new WebPaymentsService(PAYMENTS_SERVICE_URL);
    }

    @Bean
    public WebPaymentsController paymentsController() {
        return new WebPaymentsController(paymentsService());
    }

    @Bean
    public com.microservices.services.web.HomeController homeController() {
        return new HomeController();
    }


}