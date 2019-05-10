package com.globant.training.mobile.builder;

import com.globant.training.mobile.model.WeatherResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GenericBuilderTests {
    
    private static final String EXPECTED_NAME = "name";
    private static final String EXPECTED_DATE = "01/01/2019";
    private static final String EXPECTED_DESC = "name";
    private static final Double EXPECTED_TEMP = 155D;
    private static final String EXPECTED_SUN = "155";
    
    @Test
    public void testBuilder() {
        
        /* build */
        WeatherResponse weatherResponse = GenericBuilder.of(WeatherResponse::new)
                .with(WeatherResponse::setCityName, EXPECTED_NAME)
                .with(WeatherResponse::setTodayDate,EXPECTED_DATE)
                .with(WeatherResponse::setDescWeather, EXPECTED_DESC)
                .with(WeatherResponse::setTempCelsius, EXPECTED_TEMP)
                .with(WeatherResponse::setTempFahrenheit, EXPECTED_TEMP)
                .with(WeatherResponse::setSunrise, EXPECTED_SUN)
                .with(WeatherResponse::setSunset, EXPECTED_SUN)
                .build();
        
        /* review */
        assertNotNull(weatherResponse);
        assertEquals(weatherResponse.getCityName(), EXPECTED_NAME);
        assertEquals(weatherResponse.getTodayDate(), EXPECTED_DATE);
        assertEquals(weatherResponse.getDescWeather(), EXPECTED_DESC);
        assertEquals(weatherResponse.getTempCelsius(), EXPECTED_TEMP);
        assertEquals(weatherResponse.getTempFahrenheit(), EXPECTED_TEMP);
        assertEquals(weatherResponse.getSunrise(), EXPECTED_SUN);
        assertEquals(weatherResponse.getSunset(), EXPECTED_SUN);
    }
    
    @Test
    public void testBuilderWithNullProperties() {
        /* build */
        WeatherResponse weatherResponse = GenericBuilder.of(WeatherResponse::new)
                .with(WeatherResponse::setCityName, null)
                .with(WeatherResponse::setTodayDate,null)
                .with(WeatherResponse::setDescWeather, null)
                .with(WeatherResponse::setTempCelsius, null)
                .with(WeatherResponse::setTempFahrenheit, null)
                .with(WeatherResponse::setSunrise, null)
                .with(WeatherResponse::setSunset, null)
                .build();
        
        /* review */
        assertNotNull(weatherResponse);
        assertEquals(weatherResponse.getCityName(), null);
        assertEquals(weatherResponse.getTodayDate(), null);
        assertEquals(weatherResponse.getDescWeather(), null);
        assertEquals(weatherResponse.getTempCelsius(), null);
        assertEquals(weatherResponse.getTempFahrenheit(), null);
        assertEquals(weatherResponse.getSunrise(), null);
        assertEquals(weatherResponse.getSunset(), null);
    }
}
