package com.rmit.sept.bk_marketplacemicroservices.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.rmit.sept.bk_marketplacemicroservices.views.OrderView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Condition condition;
    @NotNull
    private boolean isPublisher;
    @NotNull
    private boolean isSold;
    @NotNull
    @NotBlank(message = "ISBN cannot be blank.")
    private String isbn;
    @NotNull
    @NotBlank(message = "User id cannot be blank.")
    private String userId;
    private String orderId;
    private float price;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date create_At;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date update_At;

    public Listing(Long id, Condition condition, boolean isPublisher, boolean isSold, String isbn, String userId, String orderId, float price) {
        this.id = id;
        this.condition = condition;
        this.isPublisher = isPublisher;
        this.isSold = isSold;
        this.isbn = isbn;
        this.userId = userId;
        this.orderId = orderId;
        this.price = price;
    }

    public Listing() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public boolean isPublisher() {
        return isPublisher;
    }

    public void setPublisher(boolean publisher) {
        isPublisher = publisher;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
        return "Listing{" +
                "id=" + id +
                ", condition=" + condition +
                ", isPublisher=" + isPublisher +
                ", isSold=" + isSold +
                ", isbn='" + isbn + '\'' +
                ", userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", price=" + price +
                ", create_At=" + create_At +
                ", update_At=" + update_At +
                '}';
    }
}
