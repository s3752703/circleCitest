package com.rmit.sept.bk_ordermicroservices.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.rmit.sept.bk_ordermicroservices.views.OrderView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(OrderView.Public.class)
    private Long id;
    @NotBlank(message = "Customer Id is required")
    @JsonView(OrderView.Public.class)
    private String customerId;
    @Enumerated(EnumType.STRING)
    @JsonView(OrderView.Public.class)
    @NotNull(message = "Order status must not be empty.")
    private Status status;
    @JsonView(OrderView.Public.class)
    private boolean paid;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonView(OrderView.Public.class)
    private Date create_At;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonView(OrderView.Public.class)
    private Date update_At;

    @JsonManagedReference
    @JsonView(OrderView.Internal.class)
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItems;

    public Order(Long id, String customerId, Status status, boolean paid, Date create_At, Date update_At, List<OrderLineItem> orderLineItems) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.paid = paid;
        this.create_At = create_At;
        this.update_At = update_At;
        this.orderLineItems = orderLineItems;
    }

    public Order() {

    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();

    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();

    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", status=" + status +
                ", paid=" + paid +
                ", create_At=" + create_At +
                ", update_At=" + update_At +
                ", orderLineItems=" + orderLineItems +
                '}';
    }
}
