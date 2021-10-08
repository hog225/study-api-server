package se.yg.test.apiserver.health.trainer;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/proxy-health-mvc")
@AllArgsConstructor
@Log4j2
public class TrainerProxyMvcController {

    private final TrainerProxyMvcService service;

    @GetMapping("/trainer-list")
    List<TrainerDto> getTrainerList(){
        return service.getTrainerList();
    }


}
