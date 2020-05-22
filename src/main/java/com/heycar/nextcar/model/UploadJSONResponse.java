package com.heycar.nextcar.model;

import java.util.ArrayList;
import java.util.List;

public class UploadJSONResponse {
	
	public UploadJSONResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public UploadJSONResponse(List<DealerListing> uploadJSON) {
		super();
		this.uploadJSON = uploadJSON;
	}

	List<DealerListing> uploadJSON=new ArrayList<>();

	public List<DealerListing> getUploadJSON() {
		return uploadJSON;
	}

	public void setUploadJSON(List<DealerListing> uploadJSON) {
		this.uploadJSON = uploadJSON;
	}

	
}
