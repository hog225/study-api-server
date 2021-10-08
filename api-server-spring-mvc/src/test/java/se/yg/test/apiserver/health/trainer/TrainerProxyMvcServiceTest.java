package se.yg.test.apiserver.health.trainer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TrainerProxyMvcServiceTest {


    TrainerProxyMvcService service = new TrainerProxyMvcService();

    @Test
    void trainerProxyMvcServic(){

        List<TrainerDto> trainerDtoList = service.getTrainerList();
        System.out.println(trainerDtoList);
    }

}