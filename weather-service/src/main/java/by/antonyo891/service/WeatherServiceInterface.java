package by.antonyo891.service;

import by.antonyo891.model.WeatherForecastEntity;
import by.antonyo891.model.weather.WeatherInformation;

import java.time.LocalDate;
import java.util.List;

public interface WeatherServiceInterface {
    List<WeatherForecastEntity> getWeekForecast();
    WeatherForecastEntity getForecastTemperatureForTheDay(LocalDate date);
    WeatherForecastEntity getTemperatureNow();

    WeatherInformation getWeatherInformationFromServer();



}
