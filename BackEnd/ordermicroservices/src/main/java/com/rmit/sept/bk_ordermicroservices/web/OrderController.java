package com.rmit.sept.bk_ordermicroservices.web;

import com.rmit.sept.bk_ordermicroservices.model.Order;
import com.rmit.sept.bk_ordermicroservices.model.Status;
import com.rmit.sept.bk_ordermicroservices.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    // Retrieves order by ID
    public ResponseEntity<?> getAllOrders() {
        Iterable<Order> orders = orderService.findAllOrders();
        if(orders.iterator().hasNext()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not retrieve orders.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getOrdersForSearchQuery(@RequestParam(name ="searchTerm") String searchTerm, @RequestParam(name="statusOptions") String statusOptions){
        try {
            if(statusOptions.length() > 3){
                // checks to see if filtering by status is required.
                String[] temp = statusOptions.split("~");
                List<Status> statusData = new ArrayList<>();
                for (String item: temp ) {
                    statusData.add(Status.valueOf(item.toUpperCase()));
                }
                if(searchTerm.length() > 0) {
                    // Checks to see if a search term was sent.
                    Iterable<Order> customer_orders_with_status = orderService.findByCustomerIdAndStatusIn(searchTerm, statusData);
                    return new ResponseEntity<>(customer_orders_with_status, HttpStatus.OK);
                }
                // If no search term sent but status filtering has been set, send filtered orders.
                Iterable<Order> status_orders = orderService.findAllByStatusIn(statusData);
                return new ResponseEntity<>(status_orders, HttpStatus.OK);
            }
            Iterable<Order> customer_orders = orderService.findByCustomerIdLike(searchTerm);
            return new ResponseEntity<>(customer_orders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("No orders found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{orderId}")
    // Retrieves order by ID
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") String orderId) {
        Optional<Order> order = orderService.findOrderById(Long.parseLong(orderId));
        if(order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Order could not be found.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{orderId}")
    // Retrieves order by ID
    public ResponseEntity<?> deleteOrderById(@PathVariable("orderId") String orderId) {
        boolean isDeleted = orderService.deleteOrderById(Long.parseLong(orderId));
        if(isDeleted) {
            return new ResponseEntity<>("Order has been successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Order could not be found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewOrder(@Valid @RequestBody Order order, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        orderService.saveOrUpdateOrder(order);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @PutMapping("update/{orderId}")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody Order order,@PathVariable("orderId") Long orderId, BindingResult result) {
        // Update orders
        Optional<Order> existing_order = orderService.findOrderById(orderId);
        if(existing_order.isPresent()){
            Order _order = existing_order.get();
            if(order.getStatus() != null){
                _order.setStatus(order.getStatus());
            }
            _order.setPaid(order.isPaid());
            if(order.getCustomerId() != null){
                _order.setCustomerId(order.getCustomerId());
            }
            orderService.saveOrUpdateOrder(_order);
            Map<String, Object> results = new HashMap<>();
            results.put("Message", "Successfully updated order");
            results.put("updated_order", _order);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order _order = orderService.saveOrUpdateOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/get/user")
    public ResponseEntity<Iterable<Order>> getAllOrdersByCustomerId(@RequestBody String customerId) {
        Iterable<Order> orders = orderService.findAllByCustomerId(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

//    @GetMapping("/get/status")
//    public ResponseEntity<Iterable<Order>> getAllOrdersByStatus(@RequestBody Status status) {
//        Iterable<Order> orders = orderService.findAllByStatus(status);
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }


}
