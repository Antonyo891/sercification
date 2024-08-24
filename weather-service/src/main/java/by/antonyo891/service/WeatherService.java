package by.antonyo891.service;

import by.antonyo891.WeatherProperties;
import by.antonyo891.model.LastUpdateWeatherEntity;
import by.antonyo891.model.WeatherForecastEntity;
import by.antonyo891.model.weather.WeatherInformation;
import by.antonyo891.repository.LastUpdateWeatherRepository;
import by.antonyo891.repository.WeatherRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
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
                "Connection error with the weather server"));
        List<WeatherForecastEntity> newForecasts = weatherInformation.getWeeksForecast().entrySet()
                .stream().map(w->{
                    WeatherForecastEntity forecastEntity = new WeatherForecastEntity();
                    forecastEntity.setDate(w.getKey());
                    forecastEntity.setTemperature(w.getValue());
                    return forecastEntity;
                }).toList();
        weatherRepository.deleteAll();
        weatherRepository.saveAll(newForecasts);
        if (lastUpdateWeatherRepository.count()==0) {
            lastUpdateWeatherRepository.save(new LastUpdateWeatherEntity(LocalDate.now()));
        } else {
            LastUpdateWeatherEntity lastUpdateWeatherEntity = lastUpdateWeatherRepository.findAll().getLast();
            lastUpdateWeatherEntity.setLastUpdate(LocalDate.now());
            lastUpdateWeatherRepository.save(lastUpdateWeatherEntity);
        }
        return weatherRepository.findAll();
    }

    @Override
    public List<WeatherForecastEntity> getWeekForecast() {
        if (neededToUpdateTheDatabase()){
            return updateWeather();
        }
        return weatherRepository.findAll();
    }

    @Override
    public WeatherForecastEntity getForecastTemperatureForTheDay(LocalDate date) {
        if (neededToUpdateTheDatabase()){
            updateWeather();
            return weatherRepository.findByDate(date);
        }
        return weatherRepository.findByDate(date);
    }

    @Override
    public WeatherForecastEntity getTemperatureNow() {
        if (neededToUpdateTheDatabase()){
            updateWeather();
            return weatherRepository.findByDate(now);
        }
        return weatherRepository.findByDate(now);
    }

    public WeatherInformation getWeatherInformationFromServer(){
        return getDateWeather().orElseThrow(()->new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,"Connection error with the weather server")
        );
    }

    private boolean neededToUpdateTheDatabase(){
        if (lastUpdateWeatherRepository.count()==0)
            return true;
        lastUpdateWeather = lastUpdateWeatherRepository.findAll().getLast().getLastUpdate();
        now = LocalDate.now();
        return ((lastUpdateWeather==null)||(lastUpdateWeather.isBefore(now)));
    }
}
