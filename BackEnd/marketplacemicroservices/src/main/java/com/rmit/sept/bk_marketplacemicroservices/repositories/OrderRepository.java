package com.rmit.sept.bk_marketplacemicroservices.repositories;


import com.rmit.sept.bk_marketplacemicroservices.models.Order;
import com.rmit.sept.bk_marketplacemicroservices.models.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Iterable<Order> findByUserId(String userId);

    Iterable<Order> findByUserIdContainingAndStatusIn(String userId, List<Status> statuses);

    Iterable<Order> findByUserIdContaining(String userId);

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