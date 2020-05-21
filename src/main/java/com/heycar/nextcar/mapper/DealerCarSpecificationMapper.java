package com.heycar.nextcar.mapper;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.heycar.nextcar.model.CarListing;
import com.heycar.nextcar.model.DealerCarRequestInCSV;
import com.heycar.nextcar.model.DealerCarRequestInJson;
import com.heycar.nextcar.model.DealerListing;
import com.heycar.nextcar.model.UploadCSVResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DealerCarSpecificationMapper {
	
	private UploadCSVResponse uploadCSVResponse;

	public UploadCSVResponse getUploadCSVResponse() {
		return uploadCSVResponse;
	}

	public void setUploadCSVResponse(UploadCSVResponse uploadCSVResponse) {
		this.uploadCSVResponse = uploadCSVResponse;
	}

	/**
	 * @param csv
	 * @param dealerId
	 * @return
	 * @throws IOException
	 */
	public List<DealerListing> map(MultipartFile csv, Long dealerId) throws IOException {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();

		ObjectReader objectReader = csvMapper.readerFor(DealerCarRequestInCSV.class).with(schema);

		List<DealerCarRequestInCSV> dealerCarSpecificationsInCSVList = new ArrayList<>();
		List<DealerCarRequestInCSV> validRecordsInCSVList = new ArrayList<>();
		List<DealerCarRequestInCSV> inValidRecordsInCSVList = new ArrayList<>();

		try (Reader reader = new FileReader(convertMultiPartToFile(csv))) {
			MappingIterator<DealerCarRequestInCSV> dealerCarSpecificationsMappingIterator = objectReader
					.readValues(reader);
			while (dealerCarSpecificationsMappingIterator.hasNext()) {
				dealerCarSpecificationsInCSVList.add(dealerCarSpecificationsMappingIterator.next());
			}
		}

		for (DealerCarRequestInCSV dealerCarRequestInCSV : dealerCarSpecificationsInCSVList) {
			if (dealerCarRequestInCSV.getCode() != null && dealerCarRequestInCSV.getMakeAndModel() != null
					&& dealerCarRequestInCSV.getPower() > 0 && dealerCarRequestInCSV.getColor() != null
					&& dealerCarRequestInCSV.getYear() > 0 && dealerCarRequestInCSV.getPrice() != null) {
				validRecordsInCSVList.add(dealerCarRequestInCSV);
			} else {
				inValidRecordsInCSVList.add(dealerCarRequestInCSV);
			}

			UploadCSVResponse uploadCSVResponse = setUploadCSVResponse(validRecordsInCSVList, inValidRecordsInCSVList);
			setUploadCSVResponse(uploadCSVResponse);
		}

		return validRecordsInCSVList.stream().map(dealerCarSpecificationsInCSV -> {
			String[] makeAndModel = dealerCarSpecificationsInCSV.getMakeAndModel().split("/");
			return new DealerListing(dealerId,
					new CarListing(makeAndModel[0], makeAndModel[1], dealerCarSpecificationsInCSV.getPower(),
							dealerCarSpecificationsInCSV.getColor(), dealerCarSpecificationsInCSV.getPrice(),

							dealerCarSpecificationsInCSV.getYear()),
					dealerCarSpecificationsInCSV.getCode());
		}).collect(Collectors.toList());
	}

	public UploadCSVResponse setUploadCSVResponse(List<DealerCarRequestInCSV> validRecordsInCSVList,
			List<DealerCarRequestInCSV> inValidRecordsInCSVList) {
		int successCount = validRecordsInCSVList.size();
		int failureCount = inValidRecordsInCSVList.size();
		UploadCSVResponse uploadCSVResponse = new UploadCSVResponse(successCount, failureCount, validRecordsInCSVList,
				inValidRecordsInCSVList);
		return uploadCSVResponse;
	}

	/**
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File("temp");
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	
	/**
	 *
	 * @param dealerCarSpecificationInJsonList
	 * @param dealerId
	 * @return
	 */
	public List<DealerListing> map(List<DealerCarRequestInJson> dealerCarSpecificationInJsonList, Long dealerId) {
		return dealerCarSpecificationInJsonList.stream().map(dealerCarSpecificationInJson -> {
			return new DealerListing(dealerId,
					new CarListing(dealerCarSpecificationInJson.getMake(), dealerCarSpecificationInJson.getModel(),
							dealerCarSpecificationInJson.getkW(), dealerCarSpecificationInJson.getColor(),
							dealerCarSpecificationInJson.getPrice(), dealerCarSpecificationInJson.getYear()),
					dealerCarSpecificationInJson.getCode());
		}).collect(Collectors.toList());
	}
}
