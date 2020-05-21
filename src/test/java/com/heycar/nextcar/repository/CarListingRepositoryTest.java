package com.heycar.nextcar.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.heycar.nextcar.model.CarListing;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarListingRepositoryTest{
	
	@Autowired
	private CarListingRepository repository;
	
	@Test
	public void findAll() {
		Page<CarListing> page=repository.findAll(PageRequest.of(0, 10));
		assertTrue(page.getNumberOfElements()==0);
	}
}

