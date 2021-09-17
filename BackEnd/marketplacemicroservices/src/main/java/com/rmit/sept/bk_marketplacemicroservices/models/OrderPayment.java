package com.rmit.sept.bk_marketplacemicroservices.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.rmit.sept.bk_marketplacemicroservices.views.OrderView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="payments")
public class OrderPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    @NotBlank(message = "Currency must not be empty.")
    private String currency;
    @NotBlank(message = "Methodmust not be empty.")
    private String method;
    @NotBlank(message = "Intent must not be empty.")
    private String intent;
    @NotBlank(message = "Description must not be empty.")
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date create_At;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date update_At;

    public OrderPayment(Long id, double price, String currency, String method, String intent, String description) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.method = method;
        this.intent = intent;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
