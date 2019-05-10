package com.globant.training.mobile.client;

import com.globant.training.mobile.exception.WeatherException;
import com.globant.training.mobile.thirdparty.model.WeatherAPIModel;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Unirest.class, Gson.class})
public class WeatherAPIClientTests {
    
    @Mock
    private Gson gson;
    
    @InjectMocks
    private WeatherAPIClientImpl weatherAPIClient;
    
    private static final Long SOME_CITY_ID = 1445L;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetWeatherByCityResponseStatus200() throws UnirestException, WeatherException {
        /* mock */
        GetRequest request = mock(GetRequest.class);
        HttpRequest httpRequest = mock(HttpRequest.class);
        HttpResponse<JsonNode> mockResponse = mock(HttpResponse.class);
        JsonNode mockNode = mock(JsonNode.class);
        
        PowerMockito.mockStatic(Unirest.class);
        when(Unirest.get(anyString())).thenReturn(request);
        when(request.queryString(anyString(), anyLong())).thenReturn(httpRequest);
        when(httpRequest.queryString(anyString(), anyString())).thenReturn(httpRequest);
        when(httpRequest.asJson()).thenReturn(mockResponse);
        when(mockResponse.getStatus()).thenReturn(HttpStatus.OK.value());
        when(mockResponse.getBody()).thenReturn(mockNode);
        
        when(gson.fromJson(anyString(), Matchers.<Class<WeatherAPIModel>>any())).thenReturn(new WeatherAPIModel());
        
        /* run */
        WeatherAPIModel apiModel = weatherAPIClient.getWeatherByCity(SOME_CITY_ID);
        
        /* review */
        assertNotNull(apiModel);
    }
    
    @Test(expected = WeatherException.class)
    public void testGetWeatherByCityResponseStatus404() throws UnirestException, WeatherException {
        /* mock */
        GetRequest request = mock(GetRequest.class);
        HttpRequest httpRequest = mock(HttpRequest.class);
        HttpResponse<JsonNode> mockResponse = mock(HttpResponse.class);
        JsonNode mockNode = mock(JsonNode.class);
        
        PowerMockito.mockStatic(Unirest.class);
        when(Unirest.get(anyString())).thenReturn(request);
        when(request.queryString(anyString(), anyLong())).thenReturn(httpRequest);
        when(httpRequest.queryString(anyString(), anyString())).thenReturn(httpRequest);
        when(httpRequest.asJson()).thenReturn(mockResponse);
        when(mockResponse.getStatus()).thenReturn(HttpStatus.NOT_FOUND.value());
        
        when(gson.fromJson(anyString(), Matchers.<Class<WeatherAPIModel>>any())).thenReturn(new WeatherAPIModel());
        
        /* run */
       weatherAPIClient.getWeatherByCity(SOME_CITY_ID);
    }
    
    @Test(expected = WeatherException.class)
    public void testGetWeatherByCityResponseStatus500() throws UnirestException, WeatherException {
        /* mock */
        GetRequest request = mock(GetRequest.class);
        HttpRequest httpRequest = mock(HttpRequest.class);
        HttpResponse<JsonNode> mockResponse = mock(HttpResponse.class);
        JsonNode mockNode = mock(JsonNode.class);
        
        PowerMockito.mockStatic(Unirest.class);
        when(Unirest.get(anyString())).thenReturn(request);
        when(request.queryString(anyString(), anyLong())).thenReturn(httpRequest);
        when(httpRequest.queryString(anyString(), anyString())).thenReturn(httpRequest);
        when(httpRequest.asJson()).thenReturn(mockResponse);
        when(mockResponse.getStatus()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        when(gson.fromJson(anyString(), Matchers.<Class<WeatherAPIModel>>any())).thenReturn(new WeatherAPIModel());
        
        /* run */
        weatherAPIClient.getWeatherByCity(SOME_CITY_ID);
    }
    
    @Test(expected = WeatherException.class)
    public void testGetWeatherByCityUnirestException() throws UnirestException, WeatherException {
        /* mock */
        GetRequest request = mock(GetRequest.class);
        HttpRequest httpRequest = mock(HttpRequest.class);
        
        PowerMockito.mockStatic(Unirest.class);
        when(Unirest.get(anyString())).thenReturn(request);
        when(request.queryString(anyString(), anyLong())).thenReturn(httpRequest);
        when(httpRequest.queryString(anyString(), anyString())).thenReturn(httpRequest);
        when(httpRequest.asJson()).thenThrow(new UnirestException("new UnirestException"));
    
        /* run */
        weatherAPIClient.getWeatherByCity(SOME_CITY_ID);
    }
}
