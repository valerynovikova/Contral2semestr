package ru.kpfu.novikova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.novikova.model.Weather;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    @Query(value = "select * from weather w where w.city = ?1", nativeQuery = true)
    List<Weather> getWeatherByCity(String city);
}
