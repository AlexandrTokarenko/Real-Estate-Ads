package com.example.realestateads.service;

import com.example.realestateads.dto.RealtyMAINTODO;
import com.example.realestateads.entity.Realty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final ModelMapper modelMapper;

    public List<RealtyMAINTODO> toRealtyMAINTODO(List<Realty> realties) {

        TypeMap<Realty, RealtyMAINTODO> propertyMapper = modelMapper.getTypeMap(Realty.class, RealtyMAINTODO.class);

        if (propertyMapper == null) {

            propertyMapper = modelMapper.createTypeMap(Realty.class, RealtyMAINTODO.class);

            propertyMapper.addMappings(
                    mapper -> {
                        mapper.map(Realty::getAddress, RealtyMAINTODO::setAddress);
                        mapper.map(Realty::getCurrency,RealtyMAINTODO::setFirstCurrency);
                        mapper.map(Realty::getPrice,RealtyMAINTODO::setPriceInFirstCurrency);
                    }
            );
        }
        List<RealtyMAINTODO> result = realties.stream().map(x -> modelMapper.map(x, RealtyMAINTODO.class)).toList();
        result.forEach(System.out::println);
        return result;
    }
}
