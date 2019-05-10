package com.globant.training.mobile.converter;

import com.globant.training.mobile.builder.GenericBuilder;
import com.globant.training.mobile.model.WeatherResponse;
import com.globant.training.mobile.thirdparty.model.WeatherAPIModel;
import com.globant.training.mobile.util.DateTimeUtil;
import com.globant.training.mobile.util.TemperatureUtil;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;


/**
 * Model converter for Weather app
 * @author jtapia
 * @since 04/29/2019
 * */
@Component
public class WeatherModelConverterImpl implements WeatherModelConverter {
    
    @Inject
    private DateTimeUtil dateTimeUtil;
    
    @Inject
    private TemperatureUtil temperatureUtil;
    
    private static final String MM_DD_YYYY = "MM/dd/yyyy";
    private static final String HH_MM_A = "hh:mm a";
    
    public WeatherResponse weatherApiModelToResponseModel(WeatherAPIModel apiModel) {
        if (apiModel == null) {
            return new WeatherResponse();
        }
        
        return GenericBuilder.of(WeatherResponse::new)
                .with(WeatherResponse::setCityName, apiModel.getName())
                .with(WeatherResponse::setTodayDate, dateTimeUtil.convertDateToString(MM_DD_YYYY, new Date(apiModel.getDt())))
                .with(WeatherResponse::setDescWeather,
                        Optional.ofNullable(apiModel.getWeather()).map(w -> w.get(0).getDescription()).orElseGet(() -> ""))
                .with(WeatherResponse::setTempCelsius,
                        Optional.ofNullable(apiModel.getMain()).map(m -> temperatureUtil.convertKevinToCelsius(m.getTemp())).orElseGet(() -> 0D))
                .with(WeatherResponse::setTempFahrenheit,
                        apiModel.getMain() != null ? temperatureUtil.convertKevinToFahrenheit(apiModel.getMain().getTemp()) : 0D)
                .with(WeatherResponse::setSunrise,
                        apiModel.getSys() != null? dateTimeUtil.convertUnixDateToString(HH_MM_A, apiModel.getSys().getSunrise()) : null)
                .with(WeatherResponse::setSunset,
                        apiModel.getSys() != null? dateTimeUtil.convertUnixDateToString(HH_MM_A, apiModel.getSys().getSunset()): null)
                .build();
    }
}
