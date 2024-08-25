package by.antonyo891.repository;

import by.antonyo891.model.LastUpdateWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LastUpdateWeatherRepository extends JpaRepository<LastUpdateWeatherEntity,LocalDate> {
    LastUpdateWeatherEntity findFirstByOrderByIdDesc();
}
