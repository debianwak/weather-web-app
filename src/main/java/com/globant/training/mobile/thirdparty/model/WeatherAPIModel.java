package com.globant.training.mobile.thirdparty.model;

import java.util.List;

public class WeatherAPIModel {

    private List<WeatherModel> weather;
    private MainModel main;
    
    private long dt;
    
    private SysModel sys;
    
    private String name;
    
    public List<WeatherModel> getWeather() {
        return weather;
    }
    
    public void setWeather(List<WeatherModel> weather) {
        this.weather = weather;
    }
    
    public MainModel getMain() {
        return main;
    }
    
    public void setMain(MainModel main) {
        this.main = main;
    }
    
    public long getDt() {
        return dt;
    }
    
    public void setDt(long dt) {
        this.dt = dt;
    }
    
    public SysModel getSys() {
        return sys;
    }
    
    public void setSys(SysModel sys) {
        this.sys = sys;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
