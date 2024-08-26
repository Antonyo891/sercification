package by.antonyo891.service;

import by.antonyo891.WeatherProperties;
import by.antonyo891.model.LastUpdateWeatherEntity;
import by.antonyo891.model.WeatherForecastEntity;
import by.antonyo891.model.weather.WeatherInformation;
import by.antonyo891.repository.LastUpdateWeatherRepository;
import by.antonyo891.repository.WeatherRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Data
public class WeatherService implements WeatherServiceInterface {
    @Autowired
    private WebClient webClient;
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherProperties weatherProperties;
    @Autowired
    private LastUpdateWeatherRepository lastUpdateWeatherRepository;
    private final String URI;
    private LocalDate lastUpdateWeather;
    private LocalDate now;
    private WeatherInformation weatherInformation;
    private final String BAD_CONNECTION_MESSAGE = "Connection error with the weather server";
    private final String PROBLEM_WITH_DATA = "The problem of receiving data from a third-party server";

    public WeatherService(WeatherProperties weatherProperties) {
        this.weatherProperties = weatherProperties;
        this.URI = weatherProperties.getURI();
    }

    private Optional<WeatherInformation> getDateWeather(){
        return webClient
                .get()
                .uri(URI)
                .retrieve()
                .bodyToMono(WeatherInformation.class)
                .blockOptional();
    }
    @Transactional
    private List<WeatherForecastEntity> updateWeather(){
        WeatherInformation weatherInformation = getDateWeather().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                PROBLEM_WITH_DATA + weatherProperties.getBaseUri() + URI));
        List<WeatherForecastEntity> newForecasts = weatherInformation.getWeeksForecast().entrySet()
                .stream().map(w->{
                    WeatherForecastEntity forecastEntity = new WeatherForecastEntity();
                    forecastEntity.setDate(w.getKey());
                    forecastEntity.setTemperature(w.getValue());
                    return forecastEntity;
                }).toList();
        weatherRepository.saveAll(newForecasts);
        LastUpdateWeatherEntity lastUpdateWeatherEntity = new LastUpdateWeatherEntity();
        lastUpdateWeatherEntity.setLastUpdate(LocalDate.now());
        lastUpdateWeatherRepository.save(lastUpdateWeatherEntity);
        return weatherRepository.findLast7ByOrderByDate();
    }

    @Override
    public List<WeatherForecastEntity> getWeekForecast() {
        if (neededToUpdateTheDatabase()){
            try {
                updateWeather();
            } catch (WebClientException e){
                log.error(BAD_CONNECTION_MESSAGE + weatherProperties.getBaseUri() + URI);
            }
        }
        return weatherRepository.findLast7ByOrderByDate();
    }

    @Override
    public WeatherForecastEntity getForecastTemperatureForTheDay(LocalDate date) {
        if (neededToUpdateTheDatabase()){
            try {
                updateWeather();
            } catch (WebClientException e){
                log.error(BAD_CONNECTION_MESSAGE + weatherProperties.getBaseUri() + URI);
            }
        }
        return weatherRepository.findByDate(date);
    }

    @Override
    public WeatherForecastEntity getTemperatureNow() {
        if (neededToUpdateTheDatabase()){
            try {
                updateWeather();
            } catch (WebClientException e){
                log.error(BAD_CONNECTION_MESSAGE + weatherProperties.getBaseUri() + URI);
            }
        }
        return weatherRepository.findByDate(now);
    }

    public WeatherInformation getWeatherInformationFromServer(){
        try {
            weatherInformation = getDateWeather().orElseThrow(()->new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,PROBLEM_WITH_DATA + weatherProperties.getBaseUri() + URI));
        } catch (WebClientException e){
            log.error(BAD_CONNECTION_MESSAGE + weatherProperties.getBaseUri() + URI);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }
        return weatherInformation;
    }

    private boolean neededToUpdateTheDatabase(){
        if (lastUpdateWeatherRepository.count()==0)
            return true;
        lastUpdateWeather = lastUpdateWeatherRepository.findFirstByOrderByIdDesc().getLastUpdate();
        now = LocalDate.now();
        return ((lastUpdateWeather==null)||(lastUpdateWeather.isBefore(now)));
    }
}
