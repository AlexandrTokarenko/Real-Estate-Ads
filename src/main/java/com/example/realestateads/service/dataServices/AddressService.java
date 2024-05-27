package com.example.realestateads.service.dataServices;

import com.example.realestateads.dto.AddressRequestDTO;
import com.example.realestateads.entity.Address;
import com.example.realestateads.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final RegionService regionService;
    private final LocalityService localityService;
    private final DistrictService districtService;

    public int save(Integer districtId, Integer localityId, String regionTitle) {

        Address address = checkAddress(districtId,localityId,regionTitle);

        if (address != null) return address.getId();

        return addressRepository.saveAndFlushAddressDTO(new AddressRequestDTO(regionTitle, localityId, districtId)).getId();
    }

    public Address checkAddress(Integer districtId, Integer localityId, String regionTitle) {

        return addressRepository.findByRegionAndLocalityAndDistrict(
                regionService.findById(regionTitle),
                localityService.findById(localityId),
                districtService.findById(districtId)
        ).orElse(null);
      //  return addressRepository.findByRegion_TitleAndLocality_IdAndDistrict_Id(regionTitle, localityId, districtId).orElse(null);
    }
}
