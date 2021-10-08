package se.yg.test.apiserver.health.trainer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class TrainerProxyService{

    private final WebClient client;


    public Mono<Long> addTrainer(TrainerDto trainerDTO) {

        return client
                .post()
                .uri("/trainer")
                .body(Mono.just(trainerDTO), TrainerDto.class)
                .retrieve()
                .bodyToMono(Long.class);
    }

    public Mono<Object> addTrainer1(Mono<TrainerDto> trainerDTO) {

        return client.post()
                .uri("/trainer")
                .body(trainerDTO, TrainerDto.class)
                .exchangeToMono(res->{
                    if(res.statusCode().equals(HttpStatus.OK)){
                        return res.bodyToMono(Long.class);
                    } else {
                        // 안될듯
                        Map<String, Integer> obj = new HashMap<>();
                        obj.put("code", res.statusCode().value());
                        return res.bodyToMono(Map.class);
                    }
                });
    }



    public Mono<TrainerDto> getTrainer(Long id) {
        return client.get()
                .uri("/trainer/{id}", id)
                .retrieve()
                .bodyToMono(TrainerDto.class);
    }

    public Mono<ResponseEntity<String>> getTrainer1(Long id) {
        return client.get()
                .uri("/trainer/{id}", id)
//                .exchange()
//                .log()
//                .flatMap(clientResponse -> {
//                    return clientResponse.bodyToMono(ResponseEntity.class);
//                });
                .exchangeToMono(clientResponse -> {
                    return clientResponse.toEntity(String.class);
                });
    }


    public Flux<TrainerDto> getTrainerList() {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/trainer-list")
                    .queryParam("size", 4)
                    .build())
                .exchangeToFlux(clientResponse -> {
                    return clientResponse.bodyToFlux(TrainerDto.class);
                });
    }


    public Mono<Long> deleteTrainer(Long id) {
        return null;
    }
}
