package com.heycar.nextcar.service;

import com.heycar.nextcar.model.CarListing;
import com.heycar.nextcar.model.DealerListing;
import com.heycar.nextcar.model.UploadJSONResponse;
import com.heycar.nextcar.repository.DealerListingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    private DealerListingRepository dealerListingRepository;

    @Autowired
    private CarSearchService carService;

    @Override
    public UploadJSONResponse saveOrUpdate(List<DealerListing> uploadList) {

        uploadList.forEach(carListing -> {
                    if (!dealerListingRepository.findByDealerIdAndListingId(carListing.getDealerId(), carListing.getListingId())
                            .isPresent()) {
                        create(carListing);
                    } else {
                        update(carListing);
                    }
                }
        );
        
        UploadJSONResponse uploadJSONResponse=new UploadJSONResponse(uploadList);
        return uploadJSONResponse;

    }

    @Transactional
    private long create(DealerListing carListing) {
        carService.createCar(carListing.getCarListing());
        return dealerListingRepository.save(carListing).getId();
    }

    @Transactional
    private void update(DealerListing source) {
        Optional<DealerListing> existing = dealerListingRepository
                .findByDealerIdAndListingId(source.getDealerId(), source.getListingId());
        if (existing.isPresent()) {
            DealerListing dealerCarListing = existing.get();
            CarListing sourceCar = source.getCarListing();
            CarListing targetListing = dealerCarListing.getCarListing();

            targetListing.setPrice(sourceCar.getPrice());
            targetListing.setModel(sourceCar.getModel());
            targetListing.setMake(sourceCar.getMake());
            targetListing.setkW(sourceCar.getkW());
            targetListing.setColor(sourceCar.getColor());
            targetListing.setYear(sourceCar.getYear());
            dealerListingRepository.save(dealerCarListing);
        }
    }
}
