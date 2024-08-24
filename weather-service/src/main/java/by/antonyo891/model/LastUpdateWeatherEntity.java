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
@Table(name = "last_update")
public class LastUpdateWeatherEntity {
    @Id
    @Column(name = "last_update_date")
    @JsonProperty(value = "last_update")
    private LocalDate lastUpdate;

}
