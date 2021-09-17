package com.rmit.sept.book_services.web;


import com.rmit.sept.book_services.model.Book;
import com.rmit.sept.book_services.model.BookReview;
import com.rmit.sept.book_services.services.BookReviewServices;
import com.rmit.sept.book_services.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookReviewServices bookReviewServices;
    @PostMapping("")
    public ResponseEntity<?> createNewBook(@Valid @RequestBody Book book, BindingResult result) {
        System.out.println(result);
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        Book _book = bookService.saveOrUpdateBook(book);
        return new ResponseEntity<Book>(book, HttpStatus.CREATED);
    }


    //Example: localhost:8082/api/books/
    @GetMapping("")
    // Retrieves all books
    public ResponseEntity<?> getAllBooks(){
        Iterable<Book> books = bookService.findAllBook();
        if(books.iterator().hasNext()){
            return new ResponseEntity<>(books,HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not retrieve books",HttpStatus.NOT_FOUND);

    }
/*
    //Example: localhost:8082/api/books/{ISBN}
    @GetMapping("/{ISBN}")
    //Retrieves book Principia Mathematica
    public ResponseEntity<?> getBookBySpecificISBN(){
        Book book = bookService.findBookBySpecificISBN();
        if(book!=null) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Book could not be found.", HttpStatus.NOT_FOUND);
    }

    //Example: localhost:8082/api/books/{Title}
    @GetMapping("/{Title}")
    //Retrieves book The Swoly Bible: The BroScience Way of Life
    public ResponseEntity<?> getBookBySpecificTitle(){
        Book book = bookService.findBookBySpecificTitle();
        if(book!=null) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Book could not be found.", HttpStatus.NOT_FOUND);
    }
*/
    // Example: localhost:8082/api/books/get/isbn?ISBN=1603864393
    @GetMapping("/get/isbn")
    //Retrieves book given isbn
    public ResponseEntity<?> getBookByISBN(@RequestParam(value= "ISBN", required = true) String isbn){
        Book book= bookService.findBookByISBN(isbn);
        if(book!=null) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Book could not be found.", HttpStatus.NOT_FOUND);
    }

    // Example localhost:8082/api/books/get/title?title=Principia
    @GetMapping("/get/title")
    //Retrieves book given title
    public ResponseEntity<?> getBookByTitle(@RequestParam(value= "title",required = true) String title){
        Iterable<Book> books = bookService.findBookByTitle(title);
        if(books.iterator().hasNext()) {
            return new ResponseEntity<>(books, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Book could not be found.", HttpStatus.NOT_FOUND);
    }


    // Example localhost:8082/api/books/get/author?author=George
    @GetMapping("/get/author")
    //Retrieves book given author
    public ResponseEntity<?> getBookByAuthor(@RequestParam(value= "author",required = true) String author){
        Iterable<Book> books = bookService.findBookByAuthor(author);
        if(books.iterator().hasNext()) {
            return new ResponseEntity<>(books, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Book could not be found.", HttpStatus.NOT_FOUND);
    }

    // Example localhost:8082/api/books/get/genre?genre=Parody
    @GetMapping("/get/genre")
    //Retrieves book given genre
    public ResponseEntity<?> getBookByGenre(@RequestParam(value= "genre",required = true) String genre){
        Iterable<Book> books = bookService.findBookByGenre(genre);
        if(books.iterator().hasNext()) {
            return new ResponseEntity<>(books, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Book could not be found.", HttpStatus.NOT_FOUND);
    }


    //Example :localhost:8082/api/books/get/random/?size=6
    @GetMapping("/get/random")
    //Retrieve number of books given size
    public ResponseEntity<?> getRandomNumberOfBooks(@RequestParam(value= "size",required = true) int size){
        Iterable<Book>books = bookService.findNumberOfRandomBook(size);
        if(books.iterator().hasNext()){
            return new ResponseEntity<>(books,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Book could not be found.", HttpStatus.NOT_FOUND);
    }

    //Example localhost:8082/api/books/delete/isbn?isbn=1603864393
    @DeleteMapping("delete/isbn")
    //Delete book with given isbn
    public ResponseEntity<?> deleteBookByISBN(@RequestParam(value="isbn",required = true) String ISBN){
        boolean isDelete = bookService.deleteBookByISBN(ISBN);
        if(isDelete){
            return new ResponseEntity<>("Book has been successfully deleted",HttpStatus.OK);

        }
        return new ResponseEntity<>("Book could not be found",HttpStatus.NOT_FOUND);
    }
    //Example localhost:8082/api/books/delete/title?title=Principia Mathematica - Volume Three
    @DeleteMapping("delete/title")
    //Delete book with given title
    public ResponseEntity<?> deleteBookByTitle(@RequestParam(value="title",required = true) String title){
        boolean isDelete = bookService.deleteBookByTitle(title);
        if(isDelete){
            return new ResponseEntity<>("Book has been successfully deleted",HttpStatus.OK);

        }

        return new ResponseEntity<>("Book could not be found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    //Retrieve number of books given size
    public ResponseEntity<?> getBooksBySearchTerm(@RequestParam(value= "term") String term){
        Iterable<Book>books = bookService.findBooksBySearchTerm(term);
        if(books.iterator().hasNext()){
            return new ResponseEntity<>(books,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Books could not be found by search term.", HttpStatus.NOT_FOUND);
    }
    /*
    @PutMapping("update/{orderId}")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody Order order,@PathVariable("orderId") Long orderId, BindingResult result) {
        // Update orders
        Optional<Order> existing_order = orderService.findOrderById(orderId);
        if(existing_order.isPresent()){
            Order _order = existing_order.get();
            if(order.getStatus() != null){
                _order.setStatus(order.getStatus());
            }
            _order.setPaid(order.isPaid());
            if(order.getCustomerId() != null){
                _order.setCustomerId(order.getCustomerId());
            }
            orderService.saveOrUpdateOrder(_order);
            Map<String, Object> results = new HashMap<>();
            results.put("Message", "Successfully updated order");
            results.put("updated_order", _order);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
    }

    */

}


