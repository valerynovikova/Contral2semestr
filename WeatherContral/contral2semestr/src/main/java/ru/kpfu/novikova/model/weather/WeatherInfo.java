package ru.kpfu.novikova.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherInfo {
    private List<Weather> weather;
    private String base;
    private Main main;



    @JsonProperty("weather")
    public List<Weather> getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(List<Weather> value) {
        this.weather = value;
    }

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(String value) {
        this.base = value;
    }

    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(Main value) {
        this.main = value;
    }




}
