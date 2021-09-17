package com.rmit.sept.bk_marketplacemicroservices.services;

import com.rmit.sept.bk_marketplacemicroservices.models.Condition;
import com.rmit.sept.bk_marketplacemicroservices.models.Listing;
import com.rmit.sept.bk_marketplacemicroservices.repositories.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public Listing add(Listing listing){
        return listingRepository.save(listing);
    }

    public List<Listing> findAllListings(){
        return  (List<Listing>) listingRepository.findAll();
    }

    public List<Listing> findListingsByISBN(String isbn) { return (List<Listing>) listingRepository.findListingsByIsbn(isbn);}

    public Optional<Listing> findListingById(Long id){
        return listingRepository.findById(id);
    }

    public boolean deleteListingById(Long id){
        if(listingRepository.existsById(id)){
            listingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateListingSoldStatus(Long id, boolean isPaid, String orderId){
        int row = listingRepository.updateListingSoldStatus(id, isPaid, orderId);
        if(row > 0){
            return true;
        }
        return false;
    }

    public boolean updateListing(Long id, Condition condition, float price, boolean isSold){
        int row = listingRepository.updateListing(id,isSold, price, condition);
        if(row > 0){
            return true;
        }
        return false;
    }
}
