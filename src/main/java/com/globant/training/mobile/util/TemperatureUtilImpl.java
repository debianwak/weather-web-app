package com.globant.training.mobile.util;

import org.springframework.stereotype.Component;

/**
 * Temperature util class for Weather app
 * @author jtapia
 * @since 04/29/2019
 * */
@Component
public class TemperatureUtilImpl implements TemperatureUtil {
    
    private static final Float CONSTANT_KELVIN =  273.15F;
    private static final Double NINE =  9D;
    private static final int FIVE =  5;
    private static final int THIRTY_TWO =  32;
    
    @Override
    public Double convertKevinToCelsius(float tempKevin) {
        return (double) (tempKevin - CONSTANT_KELVIN);
    }
    
    @Override
    public Double convertKevinToFahrenheit(float tempKevin) {
        return ((tempKevin - CONSTANT_KELVIN) * NINE / FIVE) + THIRTY_TWO;
    }
}
