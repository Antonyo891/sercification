package by.antonyo891.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "boilers")
@NoArgsConstructor
public class Boiler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "boilers_name",unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_of_boiler_id")
    private TypeOfBoiler typeOfBoiler;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE},
            mappedBy = "boiler")
    private Set<BoilerCondition> boilerConditions;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE},
            mappedBy = "boiler")
    private Set<BoilerConditionAccordingNTD> boilerConditionAccordingNTDS;

    public Boiler(String name, TypeOfBoiler typeOfBoiler) {
        this.name = name;
        this.typeOfBoiler = typeOfBoiler;
    }
}
