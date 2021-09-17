package com.rmit.sept.bk_marketplacemicroservices.repositories;

import com.rmit.sept.bk_marketplacemicroservices.models.Condition;
import com.rmit.sept.bk_marketplacemicroservices.models.Listing;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
@Transactional
public interface ListingRepository extends CrudRepository<Listing, Long> {

    @Modifying
    @Query(value = "UPDATE Listing ls set ls.isSold=:isSold, ls.price=:price, ls.condition=:condition where ls.id=:id")
    int updateListing(@Param("id") Long id,@Param("isSold") boolean isSold, @Param("price") float price, @Param("condition") Condition condition);

    @Modifying
    @Query(value = "UPDATE Listing ls set ls.isSold=:isSold,ls.orderId=:orderId where ls.id=:id")
    int updateListingSoldStatus(@Param("id") Long id, @Param("isSold") boolean isSold, @Param("orderId") String orderId);


    Iterable<Listing> findListingsByIsbn(String isbn);

    @Override
    Iterable<Listing> findAll();

    @Override
    Optional<Listing> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
