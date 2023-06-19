package com.microservices.vehicles;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VehicleRepository extends Repository<Vehicle, Long> {

    public Vehicle findById(Long id);

    @Query("SELECT v from Vehicle v")
    public List<Vehicle> findAll();

    @Query("SELECT count(*) from Vehicle")
    public int countVehicles();

    public void save(Vehicle vehicle);
    @Query("SELECT max(id) from Vehicle")
    public Long lastId();
}

