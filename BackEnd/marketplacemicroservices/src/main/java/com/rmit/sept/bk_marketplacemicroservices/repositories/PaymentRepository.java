package com.rmit.sept.bk_marketplacemicroservices.repositories;

import com.rmit.sept.bk_marketplacemicroservices.models.OrderPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<OrderPayment, Long> {

    @Override
    Iterable<OrderPayment> findAll();

    @Override
    Optional<OrderPayment> findById(Long aLong);
}
