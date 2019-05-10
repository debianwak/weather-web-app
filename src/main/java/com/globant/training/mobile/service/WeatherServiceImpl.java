package com.globant.training.mobile.service;

import com.globant.training.mobile.client.WeatherAPIClient;
import com.globant.training.mobile.converter.WeatherModelConverter;
import com.globant.training.mobile.exception.WeatherException;
import com.globant.training.mobile.model.WeatherResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.inject.Inject;

/**
 * Service layer for weather web app
 * @author jtapia
 * @since 04/29/2019
 * */
@Component
public class WeatherServiceImpl implements WeatherService {
    private WeatherAPIClient weatherAPIClient;
    
    @Inject
    private WeatherModelConverter weatherConverter;
    
    @Inject
    public WeatherServiceImpl(WeatherAPIClient weatherAPIClient) {
        Assert.notNull(weatherAPIClient, "weatherAPIClient must not be null!");
        this.weatherAPIClient = weatherAPIClient;
    }
    
    @Override
    public WeatherResponse getWeatherByCity(long idCity) throws WeatherException {
        return weatherConverter.weatherApiModelToResponseModel(weatherAPIClient.getWeatherByCity(idCity));
    }
}
