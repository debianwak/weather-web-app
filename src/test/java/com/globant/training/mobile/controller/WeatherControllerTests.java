package com.globant.training.mobile.controller;

import com.globant.training.mobile.exception.WeatherException;
import com.globant.training.mobile.model.WeatherResponse;
import com.globant.training.mobile.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherControllerTests {
    
    @Mock
    private WeatherService weatherService;
    
    @InjectMocks
    private WeatherController weatherController;
    
    private static final String EXPECTED_VIEW = "weatherView";
    private static final String ERROR_VIEW = "errorView";
    private static final Long SOME_CITY_ID = 1445L;
    private static final Long  INVALID_CITY_ID = 0L;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException() {
        /* construct */
        WeatherController weatherController = new WeatherController(null);
    }
    
    @Test
    public void testGetWeatherView() {
        /* run */
        Model model = new ExtendedModelMap();
        String view = weatherController.getWeatherView(model);
        
        /* review */
        assertNotNull(view);
        assertEquals(EXPECTED_VIEW, view);
    }
    
    @Test
    public void testPostWeatherParameters() throws WeatherException {
        /* mock */
        when(weatherService.getWeatherByCity(anyLong())).thenReturn(new WeatherResponse());
        
        /* run */
        String view = weatherController.postWeatherParameters(SOME_CITY_ID, new ExtendedModelMap(), new MockHttpSession(), new MockHttpServletRequest());
        
        /* review */
        assertNotNull(view);
        assertEquals(EXPECTED_VIEW, view);
        verify(weatherService, times(1)).getWeatherByCity(anyLong());
    }
    
    @Test
    public void testPostWeatherParametersWithInvalidCity() throws WeatherException {
        /* mock */
        when(weatherService.getWeatherByCity(anyLong())).thenReturn(new WeatherResponse());
        
        /* run */
        String view = weatherController.postWeatherParameters(INVALID_CITY_ID, new ExtendedModelMap(), new MockHttpSession(), new MockHttpServletRequest());
    
        /* review */
        assertNotNull(view);
        assertEquals(ERROR_VIEW, view);
        verify(weatherService, times(0)).getWeatherByCity(anyLong());
    }
    
    @Test
    public void testPostWeatherParametersException() throws WeatherException {
        /* mock */
        when(weatherService.getWeatherByCity(anyLong())).thenThrow(new WeatherException("a new exception"));
        
        /* run */
        String view = weatherController.postWeatherParameters(
                    SOME_CITY_ID, new ExtendedModelMap(), new MockHttpSession(), new MockHttpServletRequest());
        
        /* review */
        assertNotNull(view);
        assertEquals(ERROR_VIEW, view);
        verify(weatherService, times(1)).getWeatherByCity(anyLong());
    }
}
