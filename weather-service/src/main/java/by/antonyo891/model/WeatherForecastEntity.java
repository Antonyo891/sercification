package by.antonyo891.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather")
public class WeatherForecastEntity {
    @Id
    @Column(name = "date", unique = true)
    @JsonProperty(value = "date")
    private LocalDate date;
    @Column(name = "temperature")
    @JsonProperty(value = "avg_temperature")
    private Integer temperature;

}
