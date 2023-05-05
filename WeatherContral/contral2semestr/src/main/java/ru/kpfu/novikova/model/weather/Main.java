package ru.kpfu.novikova.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {
    private double temp;

    @JsonProperty("temp")
    public double getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(double value) {
        this.temp = value;
    }


}
