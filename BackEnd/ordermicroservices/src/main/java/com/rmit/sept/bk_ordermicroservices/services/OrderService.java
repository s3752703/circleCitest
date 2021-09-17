package com.rmit.sept.bk_ordermicroservices.services;

import com.rmit.sept.bk_ordermicroservices.model.Order;
import com.rmit.sept.bk_ordermicroservices.model.Status;
import com.rmit.sept.bk_ordermicroservices.repositories.OrderRepository;
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

    public List<Order> findAllByCustomerId(String customerId) {
        return (List<Order>) orderRepository.findByCustomerId(customerId);
    }

    public List<Order> findByCustomerIdLike(String customerId){
        return (List<Order>) orderRepository.findByCustomerIdContaining(customerId);
    }

    public List<Order> findByCustomerIdAndStatusIn(String customerId, List<Status> statuses){
        return (List<Order>) orderRepository.findByCustomerIdContainingAndStatusIn(customerId, statuses);
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
