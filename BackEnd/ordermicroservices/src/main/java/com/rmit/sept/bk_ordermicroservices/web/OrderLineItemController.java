package com.rmit.sept.bk_ordermicroservices.web;

import com.rmit.sept.bk_ordermicroservices.model.OrderLineItem;
import com.rmit.sept.bk_ordermicroservices.services.OrderLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oli")
public class OrderLineItemController {

    @Autowired
    private OrderLineItemService orderLineItemService;

    @GetMapping("/test")
    public ResponseEntity<Iterable<OrderLineItem>> getOrderByOrderId(@RequestBody String orderId) {
        Iterable<OrderLineItem> oli = orderLineItemService.findByOrderId((Long.parseLong(orderId)));
        System.out.println(oli.toString());
        return new ResponseEntity<>(oli, HttpStatus.OK);
    }
}
