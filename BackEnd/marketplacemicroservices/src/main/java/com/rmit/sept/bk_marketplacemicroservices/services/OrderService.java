package com.rmit.sept.bk_marketplacemicroservices.services;


import com.rmit.sept.bk_marketplacemicroservices.models.Order;
import com.rmit.sept.bk_marketplacemicroservices.models.Status;
import com.rmit.sept.bk_marketplacemicroservices.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findAllByUserId(String userId) {
        return (List<Order>) orderRepository.findByUserId(userId);
    }

    public List<Order> findByUserIdLike(String userId){
        return (List<Order>) orderRepository.findByUserIdContaining(userId);
    }

    public List<Order> findByUserIdAndStatusIn(String userId, List<Status> statuses){
        return (List<Order>) orderRepository.findByUserIdContainingAndStatusIn(userId, statuses);
    }

    public List<Order> findAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> findAllByStatusIn(List<Status> status) {
        return (List<Order>) orderRepository.findByStatusIn(status);
    }

    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public boolean deleteOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}