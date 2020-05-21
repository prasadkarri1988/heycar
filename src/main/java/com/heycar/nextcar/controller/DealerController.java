package com.heycar.nextcar.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.nextcar.exceptionhandler.ApplicationProcessException;
import com.heycar.nextcar.exceptionhandler.FileParseException;
import com.heycar.nextcar.mapper.DealerCarSpecificationMapper;
import com.heycar.nextcar.model.DealerCarRequestInJson;
import com.heycar.nextcar.model.UploadCSVResponse;
import com.heycar.nextcar.model.UploadJSONResponse;
import com.heycar.nextcar.service.DealerService;

@RestController
@RequestMapping("/upload/v1")
public class DealerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DealerController.class);

	@Autowired
	private DealerService dealerService;

	@Autowired
	private DealerCarSpecificationMapper dealerCarSpecificationMapper;

	@PostMapping(value = "/upload_csv/{dealerId}/")
	@ResponseStatus(HttpStatus.CREATED)
	public UploadCSVResponse sendDealerInfoViaCSV(@PathVariable(required = true) Long dealerId,
			@RequestParam("file") MultipartFile csv) {
		UploadCSVResponse uploadCSVResponse;
		try {
			dealerService.saveOrUpdate(dealerCarSpecificationMapper.map(csv, dealerId));
			uploadCSVResponse = dealerCarSpecificationMapper.getUploadCSVResponse();
		} catch (IOException exception) {
			LOGGER.error("IOException in sendDealerInfoViaCSV :: {} ", exception.getMessage());
			throw new FileParseException(exception.getMessage());
		} catch (Exception exception) {
			LOGGER.error("FileParseException in sendDealerInfoViaCSV :: {} ", exception.getMessage());
			throw new ApplicationProcessException(exception.getMessage());
		}

		return uploadCSVResponse;
	}

	@PostMapping(value = "/vehicle_listings/{dealerId}/")
	@ResponseStatus(HttpStatus.CREATED)
	public UploadJSONResponse sendDealerInfoViaJson(
			@Valid @RequestBody List<DealerCarRequestInJson> dealerCarSpecificationInJsonList,
			@PathVariable(required = true) Long dealerId) {
		UploadJSONResponse uploadJSONResponse;
		try {
			uploadJSONResponse = dealerService
					.saveOrUpdate(dealerCarSpecificationMapper.map(dealerCarSpecificationInJsonList, dealerId));

		} catch (Exception exception) {
			LOGGER.error("FileParseException in sendDealerInfoViaCSV :: {} ", exception.getMessage());
			throw new ApplicationProcessException(exception.getMessage());
		}

		return uploadJSONResponse;
	}

}
