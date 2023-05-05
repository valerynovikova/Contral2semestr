package ru.kpfu.novikova.services;

import ru.kpfu.novikova.dto.WeatherDto;

import java.util.List;

public interface WeatherService {

    List<WeatherDto> getWeatherByCity(String city);
}
