package com.example.realestateads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CurrencyAPI {

    private String ccy;
    private String base_ccy;
    private double buy;
    private double sale;
}
