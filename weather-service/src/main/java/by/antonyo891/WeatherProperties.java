package by.antonyo891;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "weather-service-properties")
public class WeatherProperties {
    private String baseUri;
    private String tokenName;
    private int timeout;
    private String token;
    private String latitude;
    private String longitude;
    public String getURI(){
        return String.join("&","?lat="+latitude,
                "lon="+longitude,"hours=false");
    }
}
