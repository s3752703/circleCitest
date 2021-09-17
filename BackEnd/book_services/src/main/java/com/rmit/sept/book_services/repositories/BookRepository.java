package com.rmit.sept.book_services.repositories;

import com.rmit.sept.book_services.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book,String> {

/*
    @Query("select b from Book b where b.isbn = '1603864377'")
    Book findBookBySpecificISBN();


    @Query("select b from Book b where b.title  = 'The Swoly Bible: The BroScience Way of Life'")
    Book findBookBySpecificTitle();
*/

    @Query(value = "select b from Book b where b.isbn = ?1")
    Book findBookByISBN(String isbn);

    @Query(value = "select b from Book b where b.title LIKE %?1%")
    Iterable<Book> findBookByTitle(String title);

    @Query(value = "select b from Book b where b.author LIKE %?1%")
    Iterable<Book> findBookByAuthor(String author);

    @Query(value = "select b from Book b where b.genre like  %?1%")
    Iterable<Book> findBookByGenre(String genre);

    @Query(value = "select b from Book b where b.title =?1")
    Book findExactBookByTitle(String title);

    @Query("select b from Book b where b.title= ?1")
    Book existsByTitle(String title);

    @Query("select b from Book b where lower(b.author) like %?1% or lower(b.isbn) like %?1% or lower(b.genre) like %?1% or lower(b.title) like %?1%")
    Iterable<Book> findBookByAuthorLikeOrIsbnLikeOrGenreLikeOrTitleLike(@NotBlank(message = "Term should not be empty") String term);


 @Override
    Iterable<Book> findAll();

    @Override
    Optional<Book> findById(String id);

    @Override
    boolean existsById(String id);

    @Override
    void deleteById(String id);






}
