package com.heycar.nextcar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.heycar.nextcar.model.CarListing;

@Repository
public interface CarListingRepository extends CrudRepository<CarListing, Long>,
        JpaSpecificationExecutor<CarListing> {
    Page<CarListing> findAll(Pageable pageable);
}

