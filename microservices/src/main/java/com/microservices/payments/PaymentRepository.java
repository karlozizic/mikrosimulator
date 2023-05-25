package com.microservices.payments;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PaymentRepository extends Repository<Payment, Long> {

        public Payment findById(Long id);

        @Query("SELECT p FROM Payment p")
        public List<Payment> findAll();

        @Query("SELECT count(*) FROM Payment")
        public int countPayments();

        public void save(Payment payment);

        @Query("SELECT max(id) from Payment")
        public Long lastId();

}
