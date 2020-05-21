package com.heycar.nextcar.model;

import java.util.ArrayList;
import java.util.List;

public class UploadJSONResponse {
	public UploadJSONResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public UploadJSONResponse(List<DealerListing> uploadJSONRequest) {
		super();
		this.uploadJSONRequest = uploadJSONRequest;
	}

	List<DealerListing> uploadJSONRequest=new ArrayList<>();

	public List<DealerListing> getUploadJSONRequest() {
		return uploadJSONRequest;
	}


	public void setUploadJSONRequest(List<DealerListing> uploadJSONRequest) {
		this.uploadJSONRequest = uploadJSONRequest;
	} 
	
}
