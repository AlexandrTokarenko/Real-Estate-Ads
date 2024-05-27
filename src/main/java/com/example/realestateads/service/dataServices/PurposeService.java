package com.example.realestateads.service.dataServices;

import com.example.realestateads.model.Purpose;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurposeService {

    public List<String> findAll() {

        return Arrays.stream(Purpose.values()).map(Purpose::getTitle).toList();
    }
}
