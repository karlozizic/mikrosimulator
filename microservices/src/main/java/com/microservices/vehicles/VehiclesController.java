package com.microservices.vehicles;

import com.microservices.exceptions.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/vehicles/{id}")
    public Vehicle byId(@PathVariable("id") String id) {
        logger.info("vehicles-service byId() invoked: " + id);
        Long vehicleId = Long.valueOf(id);
        Vehicle vehicle = vehicleRepository.findById(vehicleId);
        logger.info("vehicles-service byId() found: " + vehicle);

        if (vehicle == null)
            throw new VehicleNotFoundException(vehicleId);
        else {
            return vehicle;
        }
    }

}
