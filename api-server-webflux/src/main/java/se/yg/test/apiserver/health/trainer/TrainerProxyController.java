package se.yg.test.apiserver.health.trainer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/proxy-health")
@AllArgsConstructor
@Log4j2
public class TrainerProxyController {

    private final TrainerProxyService service;

    @GetMapping("/trainer/{id}")
    Mono<TrainerDto> getTrainer(@PathVariable Long id){
        return service.getTrainer(id);
    }

    @GetMapping("/trainer-list")
    Flux<TrainerDto> getTrainerList(){
        return service.getTrainerList();
    }

    @PostMapping("/trainer")
    Mono<Long> addTrainer(@RequestBody TrainerDto trainerDto){
        return service.addTrainer(trainerDto);
    }



    @GetMapping("/test/web")
    public ResponseEntity<String> handle(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(URI.create("http://localhost:8080"));
        responseHeaders.set("MyResponseHeader", "MY VSLUR'");
        return new ResponseEntity<>("hello", responseHeaders, HttpStatus.CREATED);


    }



}
