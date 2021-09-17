package com.rmit.sept.book_services.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
//import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.book_services.views.BookReviewView;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Table(name="reviews")
public class BookReview{

    private static final int min = 1;
    private static final int max = 5;

    @JsonView({BookReviewView.Public.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView({BookReviewView.Public.class})
    @NotNull
    private Long user_id;

    @JsonView({BookReviewView.Public.class})
    @Column(length = 1000)
    @NotBlank(message = "content should not be blank")
    private String content;

    @JsonView({BookReviewView.Public.class})
    @Range(min=min,max=max)
    private int star;

    @JsonView({BookReviewView.Public.class})
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private Date create_at;

    @JsonView({BookReviewView.Public.class})
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="isbn_fk")
    private Book book;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}

    public Date getCreate_At() {return create_at;}
    public void setCreate_At(Date create_at) {this.create_at = create_at;}

    public String getContent() {return this.content;}
    public void setContent(String content) {this.content = content;}

    public Long getUser_id() {return user_id;}

    public void setUser_id(Long user_id) {this.user_id = user_id;}

    public int getStar() {return star;}

    public void setStar(int star) {this.star = star;}

    public Book getBook() {return book;}

    public void setBook(Book book) {this.book = book;}

    @PrePersist
    protected void onCreate(){this.create_at = new Date();}


}
