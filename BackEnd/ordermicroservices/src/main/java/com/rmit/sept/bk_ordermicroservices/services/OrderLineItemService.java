package com.rmit.sept.bk_ordermicroservices.services;

import com.rmit.sept.bk_ordermicroservices.model.OrderLineItem;
import com.rmit.sept.bk_ordermicroservices.repositories.OrderLineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineItemService {

    @Autowired
    private OrderLineItemRepository orderLineItemRepository;

    public OrderLineItem saveOrUpdateOrderLineItem(OrderLineItem oli) {
        return orderLineItemRepository.save(oli);
    }

    public Iterable<OrderLineItem> findByOrderId(Long orderId) {
        return orderLineItemRepository.findByOrderId(orderId);
    }



}
