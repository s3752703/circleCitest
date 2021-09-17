package com.rmit.sept.book_services.repositories;


import com.rmit.sept.book_services.model.BookReview;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
@Transactional
public interface BookReviewRepository extends CrudRepository<BookReview,Long> {

    @Override
    Iterable<BookReview> findAll();

    @Override
    Optional<BookReview> findById(Long id);

    @Override
    boolean existsById(Long id);

    @Override
    void deleteById(Long id);


}

