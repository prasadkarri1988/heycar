package com.heycar.nextcar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.heycar.nextcar.model.CarListing;
import com.heycar.nextcar.repository.CarListingRepository;
import com.heycar.nextcar.repository.DealerListingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DealerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CarListingRepository repository;

  @Autowired
  private DealerListingRepository dealerRepository;

  @After
  public void after(){
       dealerRepository.deleteAll();
       repository.deleteAll();
  }

  @Test
  public void testCreateWithJSON() throws Exception {
      mockMvc.perform(post("/upload/v1/vehicle_listings/123/").content(
        "[\n"
            + "{\n"
            + "\"code\": \"a\",\n"
            + "\"make\": \"renault\",\n"
            + "\"model\": \"megane\",\n"
            + "\"kW\": 132,\n"
            + "\"year\": 2014,\n"
            + "\"color\": \"red\",\n"
            + "\"price\": 13990\n"
            + "}\n"
            + "]\n"
    )
        .contentType("application/json"))
        .andExpect(status().isCreated());

    List<CarListing> result = (List<CarListing>) repository.findAll();

    Assert.assertEquals(1, result.size());
    Assert.assertEquals("renault", result.get(0).getMake());
  }
  
  @Test
  public void testCreateWithCSV() throws Exception {
    String fileContents = "code,make/model,power-in-ps,year,color,price\n"
        + "1,mercedes/a 180,123,2014,black,15950\n"
        + "2,audi/a3,111,2016,white,17210\n"
        + "3,vw/golf,86,2018,green,14980\n"
        + "4,skoda/octavia,86,2018,16990";

    MockMultipartFile csvFile = new MockMultipartFile(
        "test.json", "", "multipart/form-data", fileContents.getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/v1/upload_csv/1/")
        .file("file", csvFile.getBytes())
        .characterEncoding("UTF-8"))
        .andExpect(status().isCreated());

    List<CarListing> result = (List<CarListing>) repository.findAll();
    Assert.assertEquals(3, result.size());
  }
}
