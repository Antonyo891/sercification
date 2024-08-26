package by.antonyo891.repository;

import by.antonyo891.model.BoilerConditionAccordingNTD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoilerNTDRepository extends JpaRepository<BoilerConditionAccordingNTD, UUID> {
}
