package se.yg.test.apiserver.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.USER_AGENT;

// WebClient.Builder Bean은 미리 존재한다.
// 그리고 WebClient 는 인터페이스임으로 구현체를 주입하여 사용해야 한다.
@Configuration
@Log4j2
public class WebClientConfig {
    @Value("${app.health-service-url}")
    private String baseUrl;

//    private ExchangeFilterFunction logRequest(){
//        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//            StringBuilder sb = new StringBuilder("Req: \n");
//            clientRequest
//                    .headers()
//                    .forEach((name, value)->{
//                        value.forEach(val->{
//                            sb.append(name + ": "+ val + "\n");
//                        });
//                    });
//            log.info(sb.toString());
//            return Mono.just(clientRequest);
//        });
//    }
//
//    private ExchangeFilterFunction logResponse(){
//        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//            StringBuilder sb = new StringBuilder("RSP: \n");
//            sb.append(clientResponse
//                    .headers().toString());
//            log.info(sb.toString());
//            return Mono.just(clientResponse);
//        });
//    }


    @Bean
    public WebClient myWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
//                .filters(exchangeFilterFunctions -> {
//                    exchangeFilterFunctions.add(logRequest());
//                    exchangeFilterFunctions.add(logResponse());
//                })
                .baseUrl(baseUrl)
                //.defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
                .defaultHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(USER_AGENT, USER_AGENT)
                .build();
    }
}
