package by.antonyo891.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherByPartsOfTheDay {
    final Integer THE_NUMBER_OF_PARTS_OF_THE_DAY = 4;
    @JsonProperty(value = "day")
    private WeatherInPartOfTheDay daytimeTemperature;
    @JsonProperty(value = "evening")
    private WeatherInPartOfTheDay temperatureInTheEvening;
    @JsonProperty(value = "morning")
    private WeatherInPartOfTheDay temperatureInTheMorning;
    @JsonProperty(value = "night")
    private WeatherInPartOfTheDay temperatureInTheNight;

    public Integer averageTemperaturePerDay(){
        return (daytimeTemperature.getAverageTemperature()
                + temperatureInTheEvening.getAverageTemperature()+
                temperatureInTheMorning.getAverageTemperature() +
                temperatureInTheNight.getAverageTemperature())/
                THE_NUMBER_OF_PARTS_OF_THE_DAY;
    }
}
