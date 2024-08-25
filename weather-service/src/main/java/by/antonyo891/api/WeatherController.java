package by.antonyo891.api;

import by.antonyo891.WeatherProperties;
import by.antonyo891.model.WeatherForecastEntity;
import by.antonyo891.model.weather.WeatherInformation;
import by.antonyo891.service.WeatherServiceInterface;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
@Data
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    WeatherServiceInterface weatherServiceInterface;
    @Autowired
    WeatherProperties weatherProperties;

    @GetMapping(path = "/week")
    @ResponseBody
    public ResponseEntity<List<WeatherForecastEntity>> getWeekForecast(){
        log.info("Weather request for the week");
        List<WeatherForecastEntity> weatherForecastEntities = weatherServiceInterface.getWeekForecast();
        log.info("Weather per week: {}",weatherForecastEntities);
        return ResponseEntity.status(HttpStatus.OK)
                .body(weatherForecastEntities);
    }

    @GetMapping(path = "/day/{date}")
    @ResponseBody
    public ResponseEntity<WeatherForecastEntity> getDayForecast(@PathVariable("date")LocalDate date){
        log.info("Weather request for the {}",date);
        WeatherForecastEntity weatherForecastEntity = weatherServiceInterface.getForecastTemperatureForTheDay(date);
        log.info("Weather per date {} : {}",weatherForecastEntity.getDate(), weatherForecastEntity.getTemperature());
        return ResponseEntity.status(HttpStatus.OK)
                .body(weatherForecastEntity);
    }

    @GetMapping(path = "/now")
    @ResponseBody
    public ResponseEntity<WeatherForecastEntity> getWeatherNow(){
        log.info("Request for the weather now");
        WeatherForecastEntity weatherForecastEntity = weatherServiceInterface.getTemperatureNow();
        log.info("Weather: {}",weatherForecastEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(weatherForecastEntity);
    }

    @GetMapping(path = "/servers")
    @ResponseBody
    public ResponseEntity<WeatherInformation> getWeatherFromServer(){
        log.info("Request for the weather from URI {}", weatherProperties.getBaseUri());
        WeatherInformation weatherInformation = weatherServiceInterface.getWeatherInformationFromServer();
        log.info("Weather: {}",weatherInformation.getFactWeather());
        return ResponseEntity.status(HttpStatus.OK)
                .body(weatherInformation);
    }
}
