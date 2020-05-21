package com.heycar.nextcar.repository;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.heycar.nextcar.model.CarListing;
import com.heycar.nextcar.model.DealerListing;
import com.heycar.nextcar.model.UploadJSONResponse;
import com.heycar.nextcar.service.DealerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DealerListingRepositoryTest {

	@Autowired
	private DealerListingRepository repository;
	
	@Autowired
	private DealerServiceImpl dealerServiceImpl;

	@Test
	public void findAll() {
		CarListing cl=new CarListing("skoda1", "octavia", 86, "green", new BigDecimal(16990), 2018);
    	DealerListing dl=new DealerListing(111, cl, "1");
    	List<DealerListing> uploadList=new ArrayList<>();
    	uploadList.add(dl);
    	UploadJSONResponse ur=dealerServiceImpl.saveOrUpdate(uploadList);
    	assertTrue(ur.getUploadJSONRequest().size()==1);
		Optional<DealerListing> page = repository.findByDealerIdAndListingId(111, "1");
		assertTrue(page.get().getDealerId()==111); 
	}
}
