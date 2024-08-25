package by.antonyo891;

import by.antonyo891.repository.LastUpdateWeatherRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WeatherApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WeatherApplication.class, args);
        LastUpdateWeatherRepository bean = context.getBean(LastUpdateWeatherRepository.class);
        System.out.println(bean.findFirstByOrderByIdDesc());

    }
}