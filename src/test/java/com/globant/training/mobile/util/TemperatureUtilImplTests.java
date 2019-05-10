package com.globant.training.mobile.util;

import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TemperatureUtilImplTests {
    
    private TemperatureUtil temperatureUtil;
    
    private DecimalFormat decimalFormat;
    
    private static final Float TEMP_EXAMPLE_KEVIN = 295F;
    private static final Float ZERO_FLOAT = 0F;
    private static final Double TEMP_CELSIUS = 21.85D;
    private static final Double TEMP_FAHRENHEIT = 71.33D;
    private static final String TWO_DECIMAL_FORMAT = "##.00";
    
    @Before
    public void setup() {
        temperatureUtil = new TemperatureUtilImpl();
        decimalFormat = new DecimalFormat(TWO_DECIMAL_FORMAT);
    }
    
    @Test
    public void testConvertKevinToCelsius() {
        /* run */
        Double tempCelsius = temperatureUtil.convertKevinToCelsius(TEMP_EXAMPLE_KEVIN);
        
        /* review */
        assertNotNull(tempCelsius);
        assertEquals(TEMP_CELSIUS, Double.valueOf(decimalFormat.format(tempCelsius)));
    }
    
    @Test
    public void testConvertKevinToCelsiusWithZero() {
        /* run */
        Double tempCelsius = temperatureUtil.convertKevinToCelsius(ZERO_FLOAT);
        
        /* review */
        assertNotNull(tempCelsius);
    }
    
    @Test
    public void testConvertKevinToCelsiusWithMaxNeg() {
        /* run */
        Double tempCelsius = temperatureUtil.convertKevinToCelsius(Float.MAX_VALUE);
        
        /* review */
        assertNotNull(tempCelsius);
    }
    
    @Test
    public void testConvertKevinToCelsiusWithMaxValue() {
        /* run */
        Double tempCelsius = temperatureUtil.convertKevinToCelsius(Float.MAX_VALUE);
        
        /* review */
        assertNotNull(tempCelsius);
    }
    
    @Test
    public void testConvertKevinToFahrenheit() {
        /* run */
        Double tempFahrenheit = temperatureUtil.convertKevinToFahrenheit(TEMP_EXAMPLE_KEVIN);
    
        /* review */
        assertNotNull(tempFahrenheit);
        assertEquals(TEMP_FAHRENHEIT, Double.valueOf(decimalFormat.format(tempFahrenheit)));
    }
    
    @Test
    public void testConvertKevinToFahrenheitWithZero() {
        /* run */
        Double tempFahrenheit = temperatureUtil.convertKevinToFahrenheit(ZERO_FLOAT);
        
        /* review */
        assertNotNull(tempFahrenheit);
    }
    
    @Test
    public void testConvertKevinToFahrenheitMaxValue() {
        /* run */
        Double tempFahrenheit = temperatureUtil.convertKevinToFahrenheit(Float.MAX_VALUE);
        
        /* review */
        assertNotNull(tempFahrenheit);
    }
    
    @Test
    public void testConvertKevinToFahrenheitMinValue() {
        /* run */
        Double tempFahrenheit = temperatureUtil.convertKevinToFahrenheit(Float.MIN_VALUE);
        
        /* review */
        assertNotNull(tempFahrenheit);
    }
}
