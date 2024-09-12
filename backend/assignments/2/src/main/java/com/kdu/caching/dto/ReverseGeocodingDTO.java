package com.kdu.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReverseGeocodingDTO{
    String name;
    String number;
    String street;
    String region;
    String country;
    String postalCode;
}