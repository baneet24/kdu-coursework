package com.kdu.caching.service;

import com.kdu.caching.constants.Constants;
import com.kdu.caching.entities.GeocodeData;
import com.kdu.caching.entities.ReverseGeocodeData;
import com.kdu.caching.exception.customexceptions.EmptyJsonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ReverseGeocodeAPIService {
    @Value("${api-key}")
    private String apiKey;

    @Value("${reverse-geocoding-url}")
    private String apiUrl;

    /**
     * @param latitude Latitude of a location
     * @param longitude Longitude of a location
     * @return ReverseGeocodeData
     * @throws BadRequestException
     * @throws EmptyJsonException
     */
    @Cacheable(value = "reverse-geocoding", key = "#latitude + ',' + #longitude", unless = "#result != null && #result.region.toLowerCase() == 'goa'")
    public ReverseGeocodeData fetchData(Double latitude, Double longitude) throws BadRequestException, EmptyJsonException {
        String apiResponse;
        try{
        String url = apiUrl + "?access_key=" + apiKey + "&query=" + latitude + "," + longitude;
        RestTemplate restTemplate = new RestTemplate();
        apiResponse = restTemplate.getForObject(url, String.class);
        }
        catch (Exception e) {
            log.error("Exception occurred while getting API response");
            throw new BadRequestException(Constants.INVALID_PARAMETER_MESSAGE);
        }
        log.info("API response retrieved successfully");
        return extractRequiredData(apiResponse);
    }

    @CacheEvict(value = "reverse-geocoding", key = "#latitude + ',' + #longitude")
    public void evictReverseGeocodeCache(String address) {
        // This method will evict the entry with the specified address from the cache
    }

    /**
     * @param apiResponse String response fetched from the api
     * @return ReverseGeocodeData
     * @throws BadRequestException
     * @throws EmptyJsonException
     */
    public ReverseGeocodeData extractRequiredData(String apiResponse) throws BadRequestException, EmptyJsonException {
        String name;
        String number;
        String street;
        String region;
        String country;
        String postalCode;
        JSONObject jsonObject = new JSONObject(apiResponse);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        if(jsonObject.length()!=0){
            try{
                JSONObject firstElement = dataArray.getJSONObject(0);
                name = firstElement.getString("name");
                number = firstElement.getString("number");
                street = firstElement.getString("street");
                region = firstElement.getString("region");
                country = firstElement.getString("country");
                postalCode = firstElement.getString("postal_code");
            }catch (Exception e) {
                log.error("Exception occurred while creating json object");
                throw new BadRequestException(Constants.INVALID_ADDRESS_MESSAGE);
            }
            log.info("Details retrieved successfully");
            return new ReverseGeocodeData(name, number, street, region, country, postalCode);
        }else{
                throw new EmptyJsonException(Constants.EMPTY_JSON_MESSAGE);
            }
    }
}

