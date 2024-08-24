package by.antonyo891.model.weather;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class WeatherInformation {
    @JsonProperty(value = "now_dt")
    private String UTC;
    @JsonProperty(value = "fact")
    private FactWeather factWeather;
    @JsonProperty(value = "forecasts")
    private ArrayList<WeatherPerDay> weatherForecast;

    public Map<LocalDate,Integer> getWeeksForecast(){
        Map<LocalDate,Integer> weeksForecast = new HashMap<>();
        weatherForecast.forEach(w->weeksForecast.put(w.getDate(),w.averageTemperature()));
        return weeksForecast;
    }
}
