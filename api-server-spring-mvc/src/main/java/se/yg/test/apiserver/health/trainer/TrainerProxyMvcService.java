package se.yg.test.apiserver.health.trainer;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TrainerProxyMvcService {

    private String baseUrl = "http://localhost:8080/health";


    private RestTemplate restTemplate = new RestTemplate();


    public List<TrainerDto> getTrainerList() {
        log.info(baseUrl + "/trainer-list");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        UriComponentsBuilder bi = UriComponentsBuilder.fromHttpUrl(baseUrl + "/trainer-list")
                .queryParam("size", 4);

        ResponseEntity<Object[]> rsp = restTemplate
                .getForEntity(bi.toUriString(), Object[].class);

//
//        log.info(rsp.getStatusCode());
//        log.info(rsp.getBody());

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(rsp.getBody())
                .map(obj -> mapper.convertValue(obj, TrainerDto.class)).collect(Collectors.toList());


    }

}
