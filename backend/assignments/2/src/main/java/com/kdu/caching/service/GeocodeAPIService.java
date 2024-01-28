package com.kdu.caching.service;

import com.kdu.caching.entities.GeocodeData;
import com.kdu.caching.exception.customexceptions.EmptyJsonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import com.kdu.caching.constants.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class GeocodeAPIService {
    @Value("${api-key}")
    private String apiKey;

    @Value("${geocoding-url}")
    private String apiUrl;

    /**
     * @param address Address of a location
     * @return GeocodeData
     * @throws BadRequestException
     * @throws EmptyJsonException
     */
    @Cacheable(value = "geocoding", key = "#address", unless = "#result != null && #result.region.toLowerCase() == 'goa'")
    public GeocodeData fetchData(String address) throws BadRequestException, EmptyJsonException {
        String apiResponse;
        try {
            String url = apiUrl + "?access_key=" + apiKey + "&query=" + address;
            RestTemplate restTemplate = new RestTemplate();
            apiResponse = restTemplate.getForObject(url, String.class);
        }catch (Exception e) {
            log.error("Exception occurred while getting API response");
            throw new BadRequestException(Constants.INVALID_PARAMETER_MESSAGE);
        }
        log.info("API response retrieved successfully");
        return extractRequiredData(apiResponse);
    }

    @CacheEvict(value = "geocoding", key = "#address")
    public void evictCache(String address){
        // For evicting cache elements
    }

    /**
     * @param apiResponse String response fetched from the api
     * @return GeocodeData
     * @throws BadRequestException
     * @throws EmptyJsonException
     */
    private GeocodeData extractRequiredData(String apiResponse) throws BadRequestException, EmptyJsonException {
        double latitude;
        double longitude;
        String region;
        JSONObject jsonObject = new JSONObject(apiResponse);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        if(jsonObject.length()!=0) {
            try {
                JSONObject firstElement = dataArray.getJSONObject(0);
                latitude = firstElement.getDouble("latitude");
                longitude = firstElement.getDouble("longitude");
                region = firstElement.getString("region");
            } catch (Exception e) {
                log.error("Exception occurred while creating json object");
                throw new BadRequestException(Constants.INVALID_ADDRESS_MESSAGE);
            }
            log.info("Details retrieved successfully");
            return new GeocodeData(latitude, longitude, region);
        }
        else{
            throw new EmptyJsonException(Constants.EMPTY_JSON_MESSAGE);
        }
    }
}
