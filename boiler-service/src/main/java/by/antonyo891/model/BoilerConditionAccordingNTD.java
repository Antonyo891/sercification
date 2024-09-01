package by.antonyo891.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "boilers_ntd")
@NoArgsConstructor
public class BoilerConditionAccordingNTD {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "steam_consumption")
    private Integer steamConsumption;

    @Column(name = "fuel_consumption")
    private Float fuelConsumption;

    @Column(name = "efficiency_coefficient")
    private Float efficiencyCoefficient;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
    @JoinColumn(name = "boiler_id")
    private Boiler boilerNTD;

    public BoilerConditionAccordingNTD(Integer steamConsumption, Float fuelConsumption, Float efficiencyCoefficient, Boiler boiler) {
        this.steamConsumption = steamConsumption;
        this.fuelConsumption = fuelConsumption;
        this.efficiencyCoefficient = efficiencyCoefficient;
        this.boilerNTD = boiler;
    }
    public BoilerConditionAccordingNTD(Integer steamConsumption, Float fuelConsumption, Float efficiencyCoefficient) {
        this.steamConsumption = steamConsumption;
        this.fuelConsumption = fuelConsumption;
        this.efficiencyCoefficient = efficiencyCoefficient;
    }
}
