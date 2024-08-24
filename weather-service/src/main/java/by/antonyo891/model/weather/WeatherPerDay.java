package by.antonyo891.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WeatherPerDay {
    @JsonProperty(value = "date")
    private LocalDate date;
    @JsonProperty(value = "parts")
    private WeatherByPartsOfTheDay partsOfTheDay;
    public Integer averageTemperature(){
        return partsOfTheDay.averageTemperaturePerDay();
    }
}
