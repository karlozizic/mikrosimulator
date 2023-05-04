package com.microservices.services.vehicles;

import com.microservices.services.registration.RegistrationServer;
import com.microservices.vehicles.VehicleRepository;
import com.microservices.vehicles.VehiclesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

@SpringBootApplication
@EnableDiscoveryClient
@Import(VehiclesConfiguration.class)
public class VehiclesServer {

    @Autowired
    protected VehicleRepository vehicleRepository;

    protected Logger logger = Logger.getLogger(VehiclesServer.class.getName());

    public static void main(String[] args) {
        // Default to registration server on localhost
        if (System.getProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME) == null)
            System.setProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME, "localhost");

        // Tell server to look for accounts-server.properties or
        // accounts-server.yml
        System.setProperty("spring.config.name", "vehicles-server");
        SpringApplication.run(VehiclesServer.class, args);
    }


}
