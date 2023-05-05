package ru.kpfu.novikova.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.novikova.aspect.Loggable;
import ru.kpfu.novikova.dto.WeatherDto;
import ru.kpfu.novikova.model.Request;
import ru.kpfu.novikova.model.User;
import ru.kpfu.novikova.model.Weather;
import ru.kpfu.novikova.model.weather.WeatherInfo;
import ru.kpfu.novikova.repository.RequestRepository;
import ru.kpfu.novikova.repository.UserRepository;
import ru.kpfu.novikova.repository.WeatherRepository;
import ru.kpfu.novikova.services.NetworkService;
import ru.kpfu.novikova.services.WeatherService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class WeatherController {

    private final NetworkService networkService;

    private final WeatherRepository weatherRepository;

    private final UserRepository userRepository;

    private final RequestRepository requestRepository;

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(NetworkService networkService, WeatherRepository weatherRepository,
                             UserRepository userRepository,
                             RequestRepository requestRepository,
                             WeatherService weatherService) {
        this.networkService = networkService;
        this.weatherRepository = weatherRepository;
        this.userRepository = userRepository;
        this.requestRepository= requestRepository;
        this.weatherService = weatherService;
    }

    @Operation(summary = "Return weather by city")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "weather was get", content = {@Content(mediaType = "application/json")})})
    @Loggable
    @GetMapping("/weather")
    public WeatherDto getWeather(@RequestParam Optional<String> city, Authentication authentication) {

        if(city.isPresent()){
            if (authentication.isAuthenticated()) {
                WeatherInfo fetchedData = networkService.fetchData(city.get());
                Weather weatherModel = new Weather(city.get(),
                        fetchedData.getWeather().get(0).getMain(),
                        Double.toString(fetchedData.getMain().getTemp()));
                WeatherDto returnModel = WeatherDto.fromModel(weatherRepository.save(weatherModel));
                System.out.println(authentication.getName());

                requestRepository.save(new Request(Timestamp.from(Instant.now()),
                        userRepository.getUserByEmail(authentication.getName()).orElse(new User()),
                        weatherModel));
                return returnModel;
            }
            return new WeatherDto();
        } else {
            return new WeatherDto();
        }
    }



    @Operation(summary = "Return all weather")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "weather was get", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/allweather")
    public Iterable<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    private Boolean isUserExistByEmail(String email) {
        Iterable<User> user = userRepository.findAll();
        return StreamSupport.stream(user.spliterator(), false)
                .anyMatch(u -> u.getEmail().equals(email));
    }

    private User getUserByEmail(String email) {
        Iterable<User> user = userRepository.findAll();
            return StreamSupport.stream(user.spliterator(), false)
                    .findFirst().orElse(new User());

    }
    @Operation(summary = "Return requests weather by city")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "requests were get", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/weather/by-city")
    public List<WeatherDto> requestByCity(@RequestParam String city) {
        return weatherService.getWeatherByCity(city);
    }

}
