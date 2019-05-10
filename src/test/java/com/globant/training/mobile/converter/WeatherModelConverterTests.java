package com.globant.training.mobile.converter;

import com.globant.training.mobile.model.WeatherResponse;
import com.globant.training.mobile.thirdparty.model.MainModel;
import com.globant.training.mobile.thirdparty.model.SysModel;
import com.globant.training.mobile.thirdparty.model.WeatherAPIModel;
import com.globant.training.mobile.thirdparty.model.WeatherModel;
import com.globant.training.mobile.util.DateTimeUtilImpl;
import com.globant.training.mobile.util.TemperatureUtilImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherModelConverterTests {
    
    @Mock
    private DateTimeUtilImpl dateTimeUtil;
    
    @Mock
    private TemperatureUtilImpl temperatureUtil;
    
    @InjectMocks
    private WeatherModelConverterImpl weatherModelConverter;
    
    private static final String STR_DATE = "04/05/2019";
    private static final String SUNNY = "sunny";
    private static final Float NEW_TEMPERATURE = 455F;
    private static final long TIME = 1557444802380L;
    private static final String CITY_NAME = "LONDON";
    private static final long SUN = 155L;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        /* mock */
        when(dateTimeUtil.convertDateToString(anyString(), any())).thenReturn(STR_DATE);
        when(dateTimeUtil.convertUnixDateToString(anyString(), anyLong())).thenReturn(STR_DATE);
        when(temperatureUtil.convertKevinToCelsius(anyFloat())).thenReturn(new Double(NEW_TEMPERATURE));
        when(temperatureUtil.convertKevinToFahrenheit(anyFloat())).thenReturn(new Double(NEW_TEMPERATURE));
    }
    
    @Test
    public void testWeatherApiModelToResponseModel()  {
        /* run */
        WeatherResponse responseModel = weatherModelConverter.weatherApiModelToResponseModel(new WeatherAPIModel());
        
        /* review*/
        assertNotNull(responseModel);
    }
    
    @Test
    public void testWeatherApiModelToResponseModelWithData()  {
        WeatherAPIModel apiModel = new WeatherAPIModel();
        
        MainModel mainModel = new MainModel();
        mainModel.setTemp(NEW_TEMPERATURE);
        
        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setDescription(SUNNY);
    
        SysModel sysModel = new SysModel();
        sysModel.setSunrise(SUN);
        sysModel.setSunset(SUN);
        
        apiModel.setDt(TIME);
        apiModel.setMain(mainModel);
        apiModel.setName(CITY_NAME);
        apiModel.setSys(sysModel);
        List<WeatherModel> listWeather = new ArrayList<>();
        listWeather.add(weatherModel);
        apiModel.setWeather(listWeather);
        
        /* run */
        WeatherResponse responseModel = weatherModelConverter.weatherApiModelToResponseModel(apiModel);
        
        /* review*/
        assertNotNull(responseModel);
        assertEquals(CITY_NAME, responseModel.getCityName());
        assertEquals(SUNNY, responseModel.getDescWeather());
    }
    
    @Test
    public void testWeatherApiModelToResponseModelNull()  {
        /* run */
        WeatherResponse responseModel = weatherModelConverter.weatherApiModelToResponseModel(null);
        
        /* review*/
        assertNotNull(responseModel);
    }
}
