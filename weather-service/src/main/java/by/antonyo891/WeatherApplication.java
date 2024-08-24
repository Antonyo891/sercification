package by.antonyo891;

import by.antonyo891.model.WeatherForecastEntity;
import by.antonyo891.repository.WeatherRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class WeatherApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WeatherApplication.class, args);
        WeatherRepository weatherRepository = context.getBean(WeatherRepository.class);
        List<WeatherForecastEntity> last = weatherRepository.findFirst7ByOrderByDateDesc();
        System.out.println(last);

    }
}