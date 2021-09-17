package com.rmit.sept.book_services.services;

import com.rmit.sept.book_services.model.Book;
import com.rmit.sept.book_services.model.BookReview;
import com.rmit.sept.book_services.repositories.BookRepository;
import com.rmit.sept.book_services.repositories.BookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookReviewServices {
    @Autowired
    private BookReviewRepository bookReviewRepository;
    @Autowired
    private BookRepository bookRepository;
    public BookReview addNewBookReview(BookReview review){return bookReviewRepository.save(review);}

    public Iterable<BookReview> findAllBookReview(){return (List<BookReview>) bookReviewRepository.findAll();}
    public Optional<BookReview> findBookReviewByID(Long id) {return bookReviewRepository.findById(id);}

    public Iterable<BookReview> findRandomNumberOfListings(int size){
        List<BookReview> listings = (List<BookReview>) bookReviewRepository.findAll();
        List<BookReview> randomReview = new ArrayList<>();
        List<Integer> index = new ArrayList<Integer>(listings.size());
        java.util.Random random = new java.util.Random();

        for(int i = 0 ; i<listings.size() ; i++){
            index.add(i);
        }
        Collections.shuffle(index);

        for(int i = 0; i < size ; i++){
            randomReview.add(listings.get(index.get(i)));
        }
        return randomReview;
    }

    public boolean deleteListingByID(Long id){

        if(bookReviewRepository.existsById(id)){
            bookReviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
