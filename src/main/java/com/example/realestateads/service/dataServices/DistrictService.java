package com.example.realestateads.service.dataServices;

import com.example.realestateads.entity.District;
import com.example.realestateads.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;

    public List<District> findAll() {
        return districtRepository.findAll();
    }

    public District findById(Integer districtId) {
        if (districtId == null) return null;
        return districtRepository.findById(districtId).orElse(null);
    }

    public int countByLocalityId(int localityId) {

        return districtRepository.countByLocalityId(localityId);
    }

    public List<District> findAllSorted() {

        return districtRepository.findAllOrderByTitle();
    }
}
