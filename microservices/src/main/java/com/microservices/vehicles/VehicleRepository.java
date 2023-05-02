package com.microservices.vehicles;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface VehicleRepository extends Repository<Vehicle, Long> {

    @Query("SELECT count(*) from Vehicle")
    public int countVehicles();
}
