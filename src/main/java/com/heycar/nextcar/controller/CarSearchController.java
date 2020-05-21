package com.heycar.nextcar.controller;

import com.heycar.nextcar.model.CarListingRequest;
import com.heycar.nextcar.model.CarListing;
import com.heycar.nextcar.service.CarSearchService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/search/v1")
@Api(value = "HayCar search API")
public class CarSearchController {

	@Autowired
	private CarSearchService carService;

	@GetMapping
	public Page<CarListing> search(@Valid @ModelAttribute CarListingRequest carSearchRequest,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
		return carService.searchCars(carSearchRequest, page, pageSize);
	}
}
