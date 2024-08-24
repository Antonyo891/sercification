package by.antonyo891.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherInPartOfTheDay {
    @JsonProperty(value = "temp_avg")
    private Integer averageTemperature;
}
