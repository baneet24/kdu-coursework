package com.kdu.caching.service;

import com.kdu.caching.dto.GeocodingDTO;
import com.kdu.caching.dto.ReverseGeocodingDTO;
import com.kdu.caching.entities.GeocodeData;
import com.kdu.caching.entities.ReverseGeocodeData;

public class DTOMapper {
    private DTOMapper(){
    }


    /**
     * convertToGeoDTO converts GeocodeData to GeocodingDTO
     * @param geocodeData Coordinates of a location
     * @return GeocodingDTO
     */
    public static GeocodingDTO convertToGeoDTO(GeocodeData geocodeData){
        return new GeocodingDTO(geocodeData.getLatitude(), geocodeData.getLongitude());
    }

    /**
     * convertToReverseGeoDTO converts ReverseGeocodeData to ReverseGeocodingDTO
     * @param reverseGeocodeData Address of a location
     * @return ReverseGeocodingDTO
     */
    public static ReverseGeocodingDTO convertToReverseGeoDTO(ReverseGeocodeData reverseGeocodeData){
        return new ReverseGeocodingDTO(reverseGeocodeData.getName(), reverseGeocodeData.getNumber(), reverseGeocodeData.getStreet(), reverseGeocodeData.getRegion(), reverseGeocodeData.getCountry(), reverseGeocodeData.getPostalCode());
    }
}
