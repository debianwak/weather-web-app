package com.globant.training.mobile.service;

import com.globant.training.mobile.exception.WeatherException;
import com.globant.training.mobile.model.WeatherResponse;

public interface WeatherService {
    
    WeatherResponse getWeatherByCity(long idCity) throws WeatherException;
}
