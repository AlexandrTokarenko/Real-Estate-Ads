package com.example.realestateads.service.dataServices;

import com.example.realestateads.dto.FlatRequestDTO;
import com.example.realestateads.dto.FlatUpdateDTO;
import com.example.realestateads.entity.Flat;
import com.example.realestateads.repository.FlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlatService {

    private final FlatRepository flatRepository;

    public Flat findByRealtyId(Integer realtyId) {

        return flatRepository.findByRealty_Id(realtyId).orElse(null);
    }

    public void saveFlatRequestDTO(FlatRequestDTO flat, int realtyId) {

        flatRepository.saveFlatRequestDTO(flat,realtyId);
    }

    public Flat findById(int flatId) {

        return flatRepository.findById(flatId).orElse(null);
    }

    public void update(FlatUpdateDTO flat) {

        flatRepository.update(flat.isParking(),flat.getFloor(),flat.getNumberOfFloors(),flat.getRealtyId());
    }
}
