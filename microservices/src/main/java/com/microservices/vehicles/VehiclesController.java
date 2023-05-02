package com.microservices.vehicles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class VehiclesController {

    protected Logger logger = Logger.getLogger(VehiclesController.class.getName());

    protected VehicleRepository vehicleRepository;

    @Autowired
    public VehiclesController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        logger.info("VehicleRepository says system has " + vehicleRepository.countVehicles() + " vehicles");
    }

}
