package com.swizzy_industries.patients_registry;

import com.swizzy_industries.patients_registry.configs.SessionConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {
                "embedded.mongodb.enabled=true",
                "embedded.mongodb.install.enabled=true",
                "spring.data.mongodb.uri=mongodb://${embedded.mongodb.host}:${embedded.mongodb.port}/${embedded.mongodb.database}"
        }
)
@Slf4j
public class IntegrationTests {

    @LocalServerPort
    int port;

    WebTestClient client;

    String token;

    @BeforeEach
    public void setup() {
        client = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();
        var exchangeResult = client.post().uri("/login").bodyValue(Map.of("username", "user", "password", "password"))
                .exchange().returnResult(Object.class);
        var headers = exchangeResult.getResponseHeaders();
        log.debug("headers: {}", headers);
        token = headers.get(SessionConfig.xAuthToken).get(0);
    }

}
