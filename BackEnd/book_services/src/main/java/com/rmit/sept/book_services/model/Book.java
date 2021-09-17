package com.rmit.sept.book_services.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.rmit.sept.book_services.views.BookView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @JsonView(BookView.Public.class)
    @Id
    @NotBlank(message = "ISBN is required")
    private String isbn;
    @JsonView(BookView.Public.class)
    @NotBlank(message = "Title should not be empty")
    private String title;
    @JsonView(BookView.Public.class)
    @NotBlank(message = "Genres should not be empty")
    private String genre;
    @JsonView(BookView.Public.class)
    @NotBlank(message = "Author should not be empty")
    private String author;
    @JsonView(BookView.Public.class)
    @NotBlank(message = "Description should not be empty")
    @Column(length = 1000)
    private String description;

    @JsonManagedReference
    @JsonView(BookView.Internal.class)
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookReview> bookReviewList;


    public Book(String ISBN, String Title, String Description,String genre, String Author,List<BookReview> bookReviewList){
        this.isbn = ISBN;
        this.title = Title;
        this.genre = genre;
        this.description = Description;
        this.author = Author;



    }

    public Book(){}

    public String getISBN(){return this.isbn;}
    public void set(String ISBN){this.isbn = ISBN;}

    public String getTitle(){return this.title;}
    public void setTitle(String Title){this.title = Title;}

    public String getGenre(){return this.genre;}
    public void setGenre(String genre){this.genre = genre;}

    public String getAuthor(){return this.author;}
    public void setAuthor(String Author){this.author = Author;}

    public String getDescription(){return this.description;}
    public void setDescription(String Description){this.description = Description;}





}
