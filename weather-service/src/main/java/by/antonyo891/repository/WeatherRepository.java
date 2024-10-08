package by.antonyo891.repository;

import by.antonyo891.model.WeatherForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherForecastEntity,LocalDate> {

    WeatherForecastEntity findByDate(LocalDate date);
    List<WeatherForecastEntity> findLast7ByOrderByDate();
}
