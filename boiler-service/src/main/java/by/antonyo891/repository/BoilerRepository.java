package by.antonyo891.repository;

import by.antonyo891.model.Boiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BoilerRepository extends JpaRepository<Boiler, UUID> {

    Optional<Boiler> findByName(String name);

}
