package by.antonyo891.repository;

import by.antonyo891.model.Boiler;
import by.antonyo891.model.BoilerCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BoilerConditionRepository extends JpaRepository<BoilerCondition, UUID> {
    Optional<BoilerCondition> findFirst1ByBoilerConditionOrderByTimeDesc(Boiler boiler);
}
