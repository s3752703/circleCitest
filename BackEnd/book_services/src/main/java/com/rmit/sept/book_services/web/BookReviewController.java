package com.rmit.sept.book_services.web;

import com.rmit.sept.book_services.model.BookReview;
import com.rmit.sept.book_services.services.BookReviewServices;
import com.rmit.sept.book_services.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/bookreview")
public class BookReviewController {
    @Autowired
    private BookReviewServices bookReviewServices;

    //Example: localhost:8082/api/bookreview
    @GetMapping("")
    //Retrieves all listings
    public ResponseEntity<?> getAllReview() {
        Iterable<BookReview> listings = bookReviewServices.findAllBookReview();
        if (listings.iterator().hasNext()) {
            return new ResponseEntity<>(listings, HttpStatus.OK);
        }
        return new ResponseEntity<>("No listing could be found", HttpStatus.OK);
    }

    //Example: localhost:8082/api/bookreview/get/id?id=1
    @GetMapping("/get/id")
    //Retrives review given id
    public ResponseEntity<?> getBookReviewById(@RequestParam(value = "id", required = true) Long id) {
        Optional<BookReview> listing = bookReviewServices.findBookReviewByID(id);
        if (listing.get() != null) {
            return new ResponseEntity<>(listing, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review could not be found", HttpStatus.NOT_FOUND);
    }

    //Example: localhost:8082/api/bookreview/get/random?size=2
    @GetMapping("/get/random")
    //Retrieves random listings give size
    public ResponseEntity<?> getRandomNumberOfListings(@RequestParam(value = "size", required = true) int size) {
        Iterable<BookReview> reviews = bookReviewServices.findRandomNumberOfListings(size);
        if (reviews.iterator().hasNext()) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        return new ResponseEntity<>("No reviews could be found", HttpStatus.OK);
    }

    //Example: localhost:8082/api/bookreview
    @PostMapping("/create")
    // Add new review
    public ResponseEntity<?> createNewBookReview(@Valid @RequestBody BookReview bookReview, BindingResult result) {
        System.out.println(result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        BookReview _review = bookReviewServices.addNewBookReview(bookReview);
        return new ResponseEntity<BookReview>(_review, HttpStatus.CREATED);
    }

    //Example: localhost:8082/api/bookreview/delete/id?id=3
    @DeleteMapping("/delete/id")
    // Delete listing give id
    public ResponseEntity<?> deleteListingById(@RequestParam(value = "id", required = true) Long id) {
        boolean isDeleted = bookReviewServices.deleteListingByID(id);
        if (isDeleted) {
            return new ResponseEntity<>("Review has been successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review could not be found", HttpStatus.NOT_FOUND);
    }


}
