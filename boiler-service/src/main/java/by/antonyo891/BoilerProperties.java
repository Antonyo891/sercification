package by.antonyo891;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "boiler-service-properties")
public class BoilerProperties {
    private final long BASE_SURVEY_RANGE;
    private final int BASIC_STEAM_LOAD_STEP;
    private final float MINIMUM_BOILER_LOAD;
}
