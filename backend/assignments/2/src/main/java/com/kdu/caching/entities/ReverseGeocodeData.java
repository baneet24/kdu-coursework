package com.kdu.caching.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReverseGeocodeData{
    String name;
    String number;
    String street;
    String region;
    String country;
    String postalCode;
}
