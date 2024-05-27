package com.example.realestateads.service.dataServices;

import com.example.realestateads.dto.HouseRequestDTO;
import com.example.realestateads.dto.HouseUpdateDTO;
import com.example.realestateads.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;

    public void save(HouseRequestDTO houseRequestDTO,Integer realtyId) {

        houseRepository.saveHouseRequestDTO(houseRequestDTO,realtyId);
    }

    public void update(HouseUpdateDTO house) {

        houseRepository.update(house.getLandArea(),house.getNumberOfFloors(),house.getRealtyId());
    }
}
