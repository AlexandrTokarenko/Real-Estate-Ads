package com.example.realestateads.service.dataServices;

import com.example.realestateads.entity.TypeOfHeating;
import com.example.realestateads.repository.TypeOfHeatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfHeatingService {

    private final TypeOfHeatingRepository typeOfHeatingRepository;

    public List<TypeOfHeating> findAll() {

        return typeOfHeatingRepository.findAll();
    }
}
