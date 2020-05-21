package com.heycar.nextcar.service;

import org.springframework.data.domain.Page;

import com.heycar.nextcar.model.CarListingRequest;
import com.heycar.nextcar.model.CarListing;

public interface CarSearchService {

    /**
     *
     * @param carListing
     * @return
     */
    public Long createCar(CarListing carListing);

    /**
     *
     * @param source
     */
    public void updateCar(CarListing source);

    /**
     *
     * @param searchParams
     * @param page
     * @param pageSize
     * @return
     */
    public Page<CarListing> searchCars(CarListingRequest searchParams, int page, int pageSize);
}
