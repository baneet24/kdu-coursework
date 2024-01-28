package com.kdu.caching.controller;


import com.kdu.caching.dto.GeocodingDTO;
import com.kdu.caching.entities.GeocodeData;
import com.kdu.caching.entities.ReverseGeocodeData;
import com.kdu.caching.exception.customexceptions.EmptyJsonException;
import com.kdu.caching.service.GeocodeAPIService;
import com.kdu.caching.service.DTOMapper;
import com.kdu.caching.service.ReverseGeocodeAPIService;
import com.kdu.caching.exception.customexceptions.InvalidAddressException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class APIController {

    private GeocodeAPIService dataService = new GeocodeAPIService();
    private ReverseGeocodeAPIService reverseDataService = new ReverseGeocodeAPIService();

    @Autowired
    public APIController(GeocodeAPIService dataService, ReverseGeocodeAPIService reverseDataService){
        this.dataService = dataService;
        this.reverseDataService = reverseDataService;
    }

    /**
     * @param address Address of  location
     * @return GeocodingDTO
     */
    @GetMapping("/geocoding")
    public GeocodingDTO fetchGeoCodeData(@RequestParam String address) throws InvalidAddressException {
        try {
            GeocodeData locationData = dataService.fetchData(address);
            return DTOMapper.convertToGeoDTO(locationData);
        }
        catch (Exception e) {
            throw new InvalidAddressException("Address is not a valid address..");
        }
    }

    /**
     * @param latitude Latitude of a location
     * @param longitude Longitude of a location
     * @return place number
     */
    @GetMapping("/reverse-geocoding")
    public String fetchReverseGeoCodeData(@RequestParam double latitude, double longitude) throws EmptyJsonException, BadRequestException {

        ReverseGeocodeData reverseGeocodeData = reverseDataService.fetchData(latitude, longitude);
        return DTOMapper.convertToReverseGeoDTO(reverseGeocodeData).getNumber();
    }

}

