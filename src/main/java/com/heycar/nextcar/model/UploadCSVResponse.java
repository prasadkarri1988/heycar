package com.heycar.nextcar.model;

import java.util.List;

public class UploadCSVResponse {

	int sucessRecordsCount;
	int failedRecordsCount;
	List<DealerCarRequestInCSV> sucessRecords;
	List<DealerCarRequestInCSV> failedRecords;

	public UploadCSVResponse() {
		// TODO Auto-generated constructor stub
	}

	
	public UploadCSVResponse(int sucessRecordsCount, int failedRecordsCount, List<DealerCarRequestInCSV> sucessRecords,
			List<DealerCarRequestInCSV> failedRecords) {
		super();
		this.sucessRecordsCount = sucessRecordsCount;
		this.failedRecordsCount = failedRecordsCount;
		this.sucessRecords = sucessRecords;
		this.failedRecords = failedRecords;
	}



	public int getSucessRecordsCount() {
		return sucessRecordsCount;
	}

	public void setSucessRecordsCount(int sucessRecordsCount) {
		this.sucessRecordsCount = sucessRecordsCount;
	}

	public int getFailedRecordsCount() {
		return failedRecordsCount;
	}

	public void setFailedRecordsCount(int failedRecordsCount) {
		this.failedRecordsCount = failedRecordsCount;
	}

	public List<DealerCarRequestInCSV> getSucessRecords() {
		return sucessRecords;
	}

	public void setSucessRecords(List<DealerCarRequestInCSV> sucessRecords) {
		this.sucessRecords = sucessRecords;
	}

	public List<DealerCarRequestInCSV> getFailedRecords() {
		return failedRecords;
	}

	public void setFailedRecords(List<DealerCarRequestInCSV> failedRecords) {
		this.failedRecords = failedRecords;
	}
	
	
	

}
