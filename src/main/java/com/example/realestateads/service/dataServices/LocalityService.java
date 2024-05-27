package com.example.realestateads.service.dataServices;

import com.example.realestateads.entity.Locality;
import com.example.realestateads.repository.LocalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalityService {

    private final LocalityRepository localityRepository;
    public List<Locality> findAll() {
        return localityRepository.findAll();
    }

    public Locality findById(Integer localityId) {

        return localityRepository.findById(localityId).orElse(null);
    }

    public List<Locality> findAllSorted() {

        return localityRepository.findAllOrderByTitle();
    }
}
