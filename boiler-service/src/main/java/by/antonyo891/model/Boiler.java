package by.antonyo891.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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

    @ManyToOne
//    @JoinColumn(name = "type_of_boiler_id")
    private TypeOfBoiler typeOfBoiler;

    @OneToMany(mappedBy = "boilerCondition")
    @JsonIgnore
    private Set<BoilerCondition> boilerConditions= new HashSet<>();
    @OneToMany(mappedBy = "boilerNTD")
    @JsonIgnore
    private Set<BoilerConditionAccordingNTD> boilerConditionAccordingNTDS = new HashSet<>();

    public Boiler(String name, TypeOfBoiler typeOfBoiler) {
        this.name = name;
        this.typeOfBoiler = typeOfBoiler;
    }
}
