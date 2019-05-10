package com.globant.training.mobile.model;

public class WeatherResponse {
    
    private String cityName;
    private String descWeather;
    private Double tempFahrenheit;
    private Double tempCelsius;
    private String sunrise;
    private String sunset;
    private String todayDate;
    
    public String getCityName() {
        return cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public String getDescWeather() {
        return descWeather;
    }
    
    public void setDescWeather(String descWeather) {
        this.descWeather = descWeather;
    }
    
    public Double getTempFahrenheit() {
        return tempFahrenheit;
    }
    
    public void setTempFahrenheit(Double tempFahrenheit) {
        this.tempFahrenheit = tempFahrenheit;
    }
    
    public Double getTempCelsius() {
        return tempCelsius;
    }
    
    public void setTempCelsius(Double tempCelsius) {
        this.tempCelsius = tempCelsius;
    }
    
    public String getSunrise() {
        return sunrise;
    }
    
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
    
    public String getSunset() {
        return sunset;
    }
    
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
    
    public String getTodayDate() {
        return todayDate;
    }
    
    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }
}
