package com.rmit.sept.bk_ordermicroservices;

import com.rmit.sept.bk_ordermicroservices.model.Order;
import com.rmit.sept.bk_ordermicroservices.model.Status;
import com.rmit.sept.bk_ordermicroservices.repositories.OrderRepository;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class OrdermicroservicesApplicationTests {

    @Autowired
    private OrderRepository orderRepository;
    List<Order> orders = new ArrayList<>();

    @Test
    public void GivenGetAllOrdersShouldReturnListOfAllOrders() {
        List<Order> orderList = (List<Order>) orderRepository.findAll();
        assertEquals("hamilton@aol.com", orderList.get(1).getCustomerId());
    }

    @Test
    public void GivenGetOrderByOrderIdShouldReturnOrderWithCorrectOrderId() {
        boolean order_exists =  orderRepository.existsById(20L);
        assertEquals(true, order_exists);
    }

    @Test
    public void GivenGetAllOrdersByCustomerIdShouldReturnListOfAllOrdersByCustomerId() {
        List<Order> orderList = (List<Order>) orderRepository.findByCustomerId("hamilton@aol.com");
        assertEquals(1, orderList.size());
    }

    @Test
    public void GivenDeleteOrderByOrderIdShouldDeleteOrderByOrderId() {
        orderRepository.save(new Order(100L, "DELETE ME", Status.COMPLETED, false, new Date(), new Date(), new ArrayList<>()));
        List<Order> orderToDelete = (List<Order>) orderRepository.findByCustomerId("DELETE ME");
        assertEquals(1, orderToDelete.size());
        Order order = orderToDelete.get(0);
        orderRepository.deleteById(order.getId());
        List<Order> deletedOrder = (List<Order>) orderRepository.findByCustomerId("DELETE ME");
        assertEquals(0, deletedOrder.size());
    }
//
//    @Test
//    public void GivenGetAllOrdersByOrderStatusShouldReturnListOfAllOrdersByOrderStatus() {
//        List<Order> orderList = (List<Order>) orderRepository.findByStatus(Status.COMPLETE);
//        assertThat(orderList, (Every.everyItem(HasPropertyWithValue.hasProperty("status", is(Status.COMPLETE)))));
//    }

    @Test
    public void GivenCreateOrderShouldReturnSuccessMessage(){
        orderRepository.save(new Order(100L, "NEW ORDER", Status.PENDING, false, new Date(), new Date(), new ArrayList<>()));
        List<Order> newOrder = (List<Order>) orderRepository.findByCustomerId("NEW ORDER");
        assertEquals(1, newOrder.size());
    }


}
