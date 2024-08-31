package by.antonyo891.repository;

import by.antonyo891.model.TypeOfBoiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TypeOfBoilerRepository extends JpaRepository<TypeOfBoiler, UUID> {
    Optional<TypeOfBoiler> findById(UUID uuid);
    Optional<TypeOfBoiler> findByName(String name);

}
