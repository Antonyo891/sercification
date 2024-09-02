package by.antonyo891.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToMany(mappedBy = "boilerCondition")
    @JsonIgnore
    private Set<BoilerCondition> boilerConditions;

    @OneToMany(mappedBy = "boilerNTD")
    @JsonIgnore
    private Set<BoilerConditionAccordingNTD> boilersNTDS;

    public Boiler(String name, TypeOfBoiler typeOfBoiler) {
        this.name = name;
        this.typeOfBoiler = typeOfBoiler;
    }
}
