package com.globant.training.mobile.client;

import com.globant.training.mobile.exception.WeatherException;
import com.globant.training.mobile.thirdparty.model.WeatherAPIModel;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.inject.Inject;

/**
 * Client for rest connection to third-party API
 * @author jtapia
 * @since 04/29/2019
 * */
@Component
public class WeatherAPIClientImpl implements  WeatherAPIClient {
    
    @Inject
    private Gson gson;
    
    @Value("${thirdpaty.weather.key}")
    private String apiKey;
    
    @Value("${thirdparty.weather.endpoint}")
    private String weatherEndpoint;
    
    private static final String WEATHER_PARAM_ID = "id";
    
    private static final String WEATHER_PARAM_APP_ID = "APPID";
    
    public WeatherAPIModel getWeatherByCity(final long idCity) throws WeatherException {
    
        HttpResponse<JsonNode> response;
        try {
            response = Unirest
                    .get(weatherEndpoint)
                    .queryString(WEATHER_PARAM_ID, idCity)
                    .queryString(WEATHER_PARAM_APP_ID, apiKey)
                    .asJson();
        } catch (UnirestException e) {
            throw new WeatherException("There are communication problems");
        }
        if (response.getStatus() == HttpStatus.OK.value()) {
            return gson.fromJson(response.getBody().toString(), WeatherAPIModel.class);
        } else {
            throw new WeatherException("Bad request");
        }
    }
}
