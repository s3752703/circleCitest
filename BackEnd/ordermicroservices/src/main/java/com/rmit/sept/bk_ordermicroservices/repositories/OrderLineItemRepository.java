package com.rmit.sept.bk_ordermicroservices.repositories;

import com.rmit.sept.bk_ordermicroservices.model.OrderLineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderLineItemRepository extends CrudRepository<OrderLineItem, Long> {

    @Override
    Optional<OrderLineItem> findById(Long aLong);
    Iterable<OrderLineItem> findByOrderId(Long orderId);

}