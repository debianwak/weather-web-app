package com.globant.training.mobile.service;

import com.globant.training.mobile.client.WeatherAPIClient;
import com.globant.training.mobile.client.WeatherAPIClientImpl;
import com.globant.training.mobile.converter.WeatherModelConverter;
import com.globant.training.mobile.exception.WeatherException;
import com.globant.training.mobile.model.WeatherResponse;
import com.globant.training.mobile.thirdparty.model.WeatherAPIModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class WeatherServiceTests {
    
    @Mock
    private WeatherAPIClient weatherAPIClient;
    
    @Mock
    private WeatherModelConverter weatherConverter;
    
    @InjectMocks
    private WeatherServiceImpl weatherService;
    
    private static final Long SOME_CITY_ID = 1445L;
    
    @Before
    public void setup() {
        weatherAPIClient = new WeatherAPIClientImpl();
        weatherService = new WeatherServiceImpl(weatherAPIClient);
        MockitoAnnotations.initMocks(this);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException() {
        WeatherServiceImpl weatherService = new WeatherServiceImpl(null);
    }
    
    @Test
    public void testGetWeatherByCity() throws WeatherException {
        /* mock */
        when(weatherAPIClient.getWeatherByCity(anyLong())).thenReturn(new WeatherAPIModel());
        when(weatherConverter.weatherApiModelToResponseModel(any())).thenReturn(new WeatherResponse());
        
        /* run */
        WeatherResponse weatherResponse = weatherService.getWeatherByCity(SOME_CITY_ID);
        
        /* review */
        assertNotNull(weatherResponse);
        verify(weatherAPIClient, times(1)).getWeatherByCity(anyLong());
        verify(weatherConverter, times(1)).weatherApiModelToResponseModel(any());
    }
}
