package com.globant.training.mobile.controller;

import com.globant.training.mobile.exception.WeatherException;
import com.globant.training.mobile.model.WeatherResponse;
import com.globant.training.mobile.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller for weather web app
 * @author jtapia
 * @since 04/29/2019
 * */
@Controller
public class WeatherController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
    
    private WeatherService weatherService;
    
    private static final long DEFAULT_CITY = 2643743;
    private static final String DATE_PARAMETER = "date";
    private static final String CITY_NAME_PARAMETER = "cityName";
    private static final String WEATHER_PARAMETER = "weather";
    private static final String FAHRENHEIT_PARAMETER = "tempFahrenheit";
    private static final String CELSIUS_PARAMETER = "tempCelsius";
    private static final String SUNRISE_PARAMETER = "sunrise";
    private static final String SUNSET_PARAMETER = "sunset";
    private static final String SELECTED_OPTION = "selectedOption";
    private static final String SHOW_RESULT = "showResult";
    private static final String WEATHER_VIEW = "weatherView";
    private static final String ERROR_VIEW = "errorView";
    private static final String ERROR = "error";
    
    @Inject
    public WeatherController(WeatherService weatherService) {
        Assert.notNull(weatherService, "WeatherService must not be null!");
        this.weatherService = weatherService;
    }
    
    @RequestMapping("/")
    public String getWeatherView(Model model) {
        model.addAttribute(SHOW_RESULT, false);
        model.addAttribute(SELECTED_OPTION, DEFAULT_CITY);
        return WEATHER_VIEW;
    }
    
    @PostMapping("/weatherCityInfo")
    public String postWeatherParameters(@RequestParam("idCity") long idCity,
                                        Model model, HttpSession session, HttpServletRequest request) {
        try {
            if (idCity <= 0) {
                return ERROR_VIEW;
            }
            
            WeatherResponse responseModel = weatherService.getWeatherByCity(idCity);
            
            model.addAttribute(DATE_PARAMETER, responseModel.getTodayDate());
            model.addAttribute(CITY_NAME_PARAMETER, responseModel.getCityName());
            model.addAttribute(WEATHER_PARAMETER, responseModel.getDescWeather());
            model.addAttribute(FAHRENHEIT_PARAMETER, responseModel.getTempFahrenheit());
            model.addAttribute(CELSIUS_PARAMETER, responseModel.getTempCelsius());
            model.addAttribute(SUNRISE_PARAMETER, responseModel.getSunrise());
            model.addAttribute(SUNSET_PARAMETER, responseModel.getSunset());
    
            model.addAttribute(SELECTED_OPTION, idCity);
            model.addAttribute(SHOW_RESULT, true);
        } catch (WeatherException ex) {
            LOGGER.error("something happening wrong", ex);
            model.addAttribute(ERROR, ex.getMessage());
            return ERROR_VIEW;
        }
        return WEATHER_VIEW;
    }
}
