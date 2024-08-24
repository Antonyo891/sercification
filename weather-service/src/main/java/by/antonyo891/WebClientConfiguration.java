package by.antonyo891;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {
    @Autowired
    private WeatherProperties weatherProperties;
    private final String BASE_URL;
    private final int TIMEOUT;
    private final String TOKEN_NAME;
    private final String TOKEN;

    public WebClientConfiguration(WeatherProperties weatherProperties) {
        this.weatherProperties = weatherProperties;
        this.BASE_URL = weatherProperties.getBaseUri();
        this.TIMEOUT = weatherProperties.getTimeout();
        this.TOKEN = weatherProperties.getToken();
        this.TOKEN_NAME = weatherProperties.getTokenName();
    }

    @Bean
    public WebClient webClientWeather() {
        final var tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                });
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(TOKEN_NAME,TOKEN)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
