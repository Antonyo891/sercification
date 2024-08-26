package by.antonyo891.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "boiler_id",nullable = false)
    private Boiler boiler;

    public BoilerConditionAccordingNTD(Integer steamConsumption, Float fuelConsumption, Float efficiencyCoefficient, Boiler boiler) {
        this.steamConsumption = steamConsumption;
        this.fuelConsumption = fuelConsumption;
        this.efficiencyCoefficient = efficiencyCoefficient;
        this.boiler = boiler;
    }
}
