package com.example.realestateads.service.dataServices;

import com.example.realestateads.entity.Region;
import com.example.realestateads.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public Region findById(String title) {
        return regionRepository.findById(title).orElse(null);
    }
    public List<Region> findAll() {

        return regionRepository.findAll();
    }

    public List<Region> findAllSorted() {

        return regionRepository.findAllOrderByTitle();
    }
}
