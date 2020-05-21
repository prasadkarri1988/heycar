package com.heycar.nextcar.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.heycar.nextcar.model.CarListing;
import com.heycar.nextcar.model.CarListingRequest;
import com.heycar.nextcar.repository.CarListingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarSearchServiceImplTest {

	@Autowired
	private CarSearchServiceImpl carSearchServiceImpl;

	@Autowired
	private CarListingRepository repository;

	@Test
	public void createCarTestForListing() {
		long id = carSearchServiceImpl
				.createCar(new CarListing("skoda1", "octavia", 86, "green", new BigDecimal(16990), 2018));
		assertTrue(id > 0);
	}

	@Test
	public void updateCar() {
		CarListing cl = new CarListing("vw", "golf", 86, "green", new BigDecimal(14980), 2018);
		long id = carSearchServiceImpl.createCar(cl);
		assertTrue(id > 0);
		cl.setPrice(new BigDecimal(16900));
		carSearchServiceImpl.updateCar(cl);
		Optional<CarListing> carListingUpdate = repository.findById(id);
		assertTrue(carListingUpdate.get().getYear().equals(2018));
	}

	@Test
	public void getSearchCriteriaByColor() {
		CarListing cl = new CarListing("vw", "golf", 86, "red", new BigDecimal(14980), 2018);
		long id = carSearchServiceImpl.createCar(cl);
		assertTrue(id > 0);
		CarListingRequest carListingRequest = new CarListingRequest();
		carListingRequest.setColor("red");
		Page<CarListing> searchPages = carSearchServiceImpl.searchCars(carListingRequest, 0, 10);
		System.out.println(searchPages.getNumberOfElements());
		assertTrue(searchPages.getNumberOfElements() == 1);
	}

	@Test
	public void getSearchCriteriaByYear() {
		CarListing cl = new CarListing("vw", "golf", 86, "red", new BigDecimal(14980), 1995);
		long id = carSearchServiceImpl.createCar(cl);
		assertTrue(id > 0);
		CarListingRequest carListingRequest = new CarListingRequest();
		carListingRequest.setYear(1995);
		Page<CarListing> searchPages = carSearchServiceImpl.searchCars(carListingRequest, 0, 10);
		System.out.println(searchPages.getNumberOfElements());
		assertTrue(searchPages.getNumberOfElements() == 1);
	}

	@Test
	public void getSearchCriteriaByMake() {
		CarListing cl = new CarListing("vw123", "golf", 86, "red", new BigDecimal(14980), 2001);
		long id = carSearchServiceImpl.createCar(cl);
		assertTrue(id > 0);
		CarListingRequest carListingRequest = new CarListingRequest();
		carListingRequest.setMake("vw123");
		Page<CarListing> searchPages = carSearchServiceImpl.searchCars(carListingRequest, 0, 10);
		assertTrue(searchPages.getNumberOfElements() == 1);
	}

	@Test
	public void getSearchCriteriaByModel() {
		CarListing cl = new CarListing("vw1", "golf121", 86, "red", new BigDecimal(14980), 2001);
		long id = carSearchServiceImpl.createCar(cl);
		assertTrue(id > 0);
		CarListingRequest carListingRequest = new CarListingRequest();
		carListingRequest.setModel("golf121");
		Page<CarListing> searchPages = carSearchServiceImpl.searchCars(carListingRequest, 0, 10);
		assertTrue(searchPages.getNumberOfElements() == 1);
	}

}
