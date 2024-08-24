package by.antonyo891;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class WeatherApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WeatherApplication.class, args);

    }
}