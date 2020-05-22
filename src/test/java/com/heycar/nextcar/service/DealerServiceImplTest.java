package com.heycar.nextcar.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.heycar.nextcar.model.CarListing;
import com.heycar.nextcar.model.DealerListing;
import com.heycar.nextcar.model.UploadJSONResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DealerServiceImplTest {

	@Autowired
    private DealerServiceImpl dealerServiceImpl;
    
    @Test
    public void saveOrUpdateJSONFileData() {
    	CarListing cl=new CarListing("skoda1", "octavia", 86, "green", new BigDecimal(16990), 2018);
    	DealerListing dl=new DealerListing(111, cl, "1");
    	List<DealerListing> uploadList=new ArrayList<>();
    	uploadList.add(dl);
    	UploadJSONResponse ur=dealerServiceImpl.saveOrUpdate(uploadList);
    	assertTrue(ur.getUploadJSON().size()==1);
    }
    
    
    @Test
    public void saveOrUpdateJSONFileDataMultipleObjects() {
    	CarListing cl=new CarListing("skoda1", "octavia", 86, "green", new BigDecimal(16990), 2018);
    	DealerListing dl=new DealerListing(111, cl, "1");
    	List<DealerListing> uploadList=new ArrayList<>();
    	uploadList.add(dl);
    	CarListing cl2=new CarListing("skoda1", "octavia", 86, "green", new BigDecimal(16990), 2018);
    	DealerListing dl2=new DealerListing(111, cl2, "1");
    	uploadList.add(dl2);
    	UploadJSONResponse ur=dealerServiceImpl.saveOrUpdate(uploadList);
    	assertTrue(ur.getUploadJSON().size()==2);
    }
    
    
    
}
