package com.rmit.sept.bk_marketplacemicroservices.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.rmit.sept.bk_marketplacemicroservices.views.OrderView;

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
    private String userId;
    @Enumerated(EnumType.STRING)
    @JsonView(OrderView.Public.class)
    @NotNull(message = "Order status must not be empty.")
    private Status status;
    @JsonView(OrderView.Public.class)
    private String payment_id;
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


    public Order(Long id, String userId, Status status, String payment_id, Date create_At, Date update_At, List<OrderLineItem> orderLineItems) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.payment_id = payment_id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreate_At() {
        return create_At;
    }


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
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
                ", userId='" + userId + '\'' +
                ", status=" + status +
                ", payment_id='" + payment_id + '\'' +
                ", create_At=" + create_At +
                ", update_At=" + update_At +
                ", orderLineItems=" + orderLineItems +
                '}';
    }
}