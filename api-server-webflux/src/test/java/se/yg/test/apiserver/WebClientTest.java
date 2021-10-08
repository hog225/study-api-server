package se.yg.test.apiserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import se.yg.test.apiserver.health.trainer.TrainerDto;

import java.util.*;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.USER_AGENT;

public class WebClientTest {
    @Value("${app.health-service-url}")
    private String baseUrl;

    @Test
    void postTest(){
        System.out.println("fefe");
    }
}
