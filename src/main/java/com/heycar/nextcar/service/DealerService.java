package com.heycar.nextcar.service;

import java.util.List;

import com.heycar.nextcar.model.DealerListing;
import com.heycar.nextcar.model.UploadJSONResponse;

public interface DealerService {

    /**
     *
     * @param uploadList
     */
    public UploadJSONResponse saveOrUpdate(List<DealerListing> uploadList);
}
