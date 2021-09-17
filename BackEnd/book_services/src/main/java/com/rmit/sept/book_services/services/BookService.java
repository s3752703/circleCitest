package com.rmit.sept.book_services.services;


import com.rmit.sept.book_services.model.Book;
import com.rmit.sept.book_services.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book saveOrUpdateBook(Book book){return bookRepository.save(book);}
    public Iterable<Book>findAllBook(){ return (List<Book>) bookRepository.findAll();}
    //public  Book findBookBySpecificISBN(){return bookRepository.findBookBySpecificISBN();}
    //public  Book findBookBySpecificTitle(){return bookRepository.findBookBySpecificTitle();}

    public Iterable<Book> findNumberOfRandomBook(int size){
        List<Book> books = (List<Book>) bookRepository.findAll();
        java.util.Random random = new java.util.Random();
        List<Book> randomBooks = new ArrayList<>();
        List<Integer> index = new ArrayList<Integer>(books.size());

        for(int i =0; i<books.size();i++){
            index.add(i);
        }
        Collections.shuffle(index);

        for(int i =0;i<size;i++){
            randomBooks.add(books.get(index.get(i)));
        }
        return randomBooks;
    }

    public boolean deleteBookByISBN(String ISBN){
        if(bookRepository.existsById(ISBN)){
            bookRepository.deleteById(ISBN);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public boolean deleteBookByTitle(String title){
        Book book = bookRepository.findExactBookByTitle(title);
        boolean isDelete =  deleteBookByISBN(book.getISBN());
        return isDelete;
    }

    public Book findBookByISBN(String ISBN){
        Book books = bookRepository.findBookByISBN(ISBN);
        return books;

    }

    public Iterable<Book> findBookByTitle(String Title){
        Iterable<Book> books = bookRepository.findBookByTitle(Title);
        return (List<Book>)books;
    }

    public Iterable<Book> findBookByAuthor(String author){
        Iterable<Book> books = bookRepository.findBookByAuthor(author);
        return (List<Book>)books;
    }

    public Iterable<Book> findBookByGenre(String genre){
        Iterable<Book> books = bookRepository.findBookByGenre(genre);
        return (List<Book>)books;
    }

    public Iterable<Book> findBooksBySearchTerm(String term){
        Iterable<Book> books = bookRepository.findBookByAuthorLikeOrIsbnLikeOrGenreLikeOrTitleLike(term);
        return books;
    }


}
