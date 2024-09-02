package by.antonyo891.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "boilers_types")
@NoArgsConstructor
@JsonIgnoreProperties({"boilers"})
public class TypeOfBoiler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "type_name",unique = true,nullable = false)
    private String name;
    @Column(name = "nominal_steam_capacity",length =4)
    private Integer nominalSteamCapacity;
    @Column(name = "nominal_steam_pressure", length = 3)
    private Integer nominalSteamPressure;
    @Column(name = "nominal_steam_temperature", length = 3)
    private Integer nominalSteamTemperature;
    @OneToMany(
    mappedBy = "typeOfBoiler")
    @JsonIgnore
    private Set<Boiler> boilers;

    public TypeOfBoiler(String name, Integer nominalSteamCapacity, Integer nominalSteamPressure, Integer nominal_Steam_temperature) {
        this.name = name;
        this.nominalSteamCapacity = nominalSteamCapacity;
        this.nominalSteamPressure = nominalSteamPressure;
        this.nominalSteamTemperature = nominal_Steam_temperature;
    }
}
