package com.rmit.sept.bk_marketplacemicroservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.SQLUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 1, message = "Order line number cannot be below 0.")
    private int orderLineNumber;

    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters.")
    @NotBlank(message = "ISBN is required.")
    private String isbn;

    @Min(value = 0, message = "Price cannot be below zero.")
    private double netAmount;

    @Min(value = 0, message = "Price cannot be below zero.")
    private double taxAmount;

    @Min(value = 0, message = "Price cannot be below zero.")
    private double totalAmount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="order_id")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(int orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}