package com.heycar.nextcar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.heycar.nextcar.model.DealerListing;

import java.util.Optional;

@Repository
public interface DealerListingRepository extends CrudRepository<DealerListing, Long> {
    Optional<DealerListing> findByDealerIdAndListingId(long dealerId, String listingId);
}
