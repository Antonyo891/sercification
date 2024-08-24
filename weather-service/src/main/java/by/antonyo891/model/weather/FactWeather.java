package by.antonyo891.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FactWeather {
    @JsonProperty(value = "temp")
    private Integer factTemperatureNow;
}
