package com.rmit.sept.bk_marketplacemicroservices.web;

import com.rmit.sept.bk_marketplacemicroservices.models.Condition;
import com.rmit.sept.bk_marketplacemicroservices.models.Listing;
import com.rmit.sept.bk_marketplacemicroservices.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping("")
    //Retrieves all listings
    public ResponseEntity<?> getAllListings() {

        List<Listing> listings = listingService.findAllListings();
        Map<String, String> output = new HashMap<>();
        if (listings.iterator().hasNext()) {
            return new ResponseEntity<>(listings, HttpStatus.OK);
        }
        output.put("Message", "No listings could be found.");
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getListingById(@PathVariable(name = "id") Long id){
        Optional<Listing> opt_listing = listingService.findListingById(id);
        Map<String, String> output = new HashMap<>();
        if(opt_listing.isPresent()){
            Listing listing = opt_listing.get();
            return new ResponseEntity<>(listing, HttpStatus.OK);
        }
        output.put("Message", "Listing could not be found");
        return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> getListingById(@PathVariable(name = "isbn") String isbn){
        Iterable<Listing> listings = listingService.findListingsByISBN(isbn);
        Map<String, String> output = new HashMap<>();
        if(listings.iterator().hasNext()){
            return new ResponseEntity<>(listings, HttpStatus.OK);
        }
        output.put("Message", "Listings could not be found");
        return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createListing(@Valid @RequestBody Listing listing, BindingResult result){
        System.out.println(listing.toString());
        if(result.hasErrors()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        listingService.add(listing);
        return new ResponseEntity<>(listing, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteListing(@PathVariable(name = "id") Long id){
        Map<String, String> output = new HashMap<>();
        boolean success = listingService.deleteListingById(id);
        if(success) {
            output.put("success", "true");
            output.put("Message", "Listing has been successfully deleted.");
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
        output.put("success", "false");
        output.put("Message", "Listing has not been deleted");
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> changeListingStatus(@PathVariable(name = "id") Long id, @RequestParam(name = "isPaid") boolean isPaid, @RequestParam(name = "orderId") String orderId) {
        Map<String, String> output = new HashMap<>();
        boolean success = listingService.updateListingSoldStatus(id, isPaid, orderId);
        if(success) {
            output.put("success", "true");
            output.put("Message", "Listing sold status has been updated.");
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
        output.put("success", "false");
        output.put("Message", "Listing sold status has not been updated.");
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateListing(@PathVariable(name = "id") Long id,@RequestParam(name = "isSold") boolean isSold,@RequestParam(name = "price") float price,@RequestParam(name = "condition") Condition condition){
        Map<String, String> output = new HashMap<>();
        boolean success = listingService.updateListing(id, condition,price, isSold);
        if(success) {
            output.put("success", "true");
            output.put("Message", "Listing has been updated.");
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
        output.put("success", "false");
        output.put("Message", "Listing has not been updated.");
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
