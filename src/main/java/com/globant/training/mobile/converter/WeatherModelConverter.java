package com.globant.training.mobile.converter;

import com.globant.training.mobile.model.WeatherResponse;
import com.globant.training.mobile.thirdparty.model.WeatherAPIModel;

public interface WeatherModelConverter {
    WeatherResponse weatherApiModelToResponseModel(WeatherAPIModel apiModel);
}
