package com.example.realestateads.service.dataServices;

import com.example.realestateads.entity.Construction;
import com.example.realestateads.repository.ConstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionService {

    private final ConstructionRepository constructionRepository;

    public List<Construction> findAllByPropertyTitle(String propertyTitle) {

        return constructionRepository.findAllByPropertyTitle(propertyTitle);
    }
}
