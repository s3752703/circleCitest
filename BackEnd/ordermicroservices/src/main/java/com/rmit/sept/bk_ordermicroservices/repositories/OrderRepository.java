package com.rmit.sept.bk_ordermicroservices.repositories;

import com.rmit.sept.bk_ordermicroservices.model.Order;
import com.rmit.sept.bk_ordermicroservices.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Iterable<Order> findByCustomerId(String customerId);

    Iterable<Order> findByCustomerIdContainingAndStatusIn(String customerId, List<Status> statuses);

    Iterable<Order> findByCustomerIdContaining(String customerId);

    Iterable<Order> findByStatusIn(List<Status> statuses);


    @Override
    Iterable<Order> findAll();

    @Override
    Optional<Order> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    boolean existsById(Long id);
}
