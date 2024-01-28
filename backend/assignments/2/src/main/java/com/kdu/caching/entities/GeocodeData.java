package com.kdu.caching.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeocodeData {

    private final double latitude;
    private final double longitude;
    private final String region;
}
