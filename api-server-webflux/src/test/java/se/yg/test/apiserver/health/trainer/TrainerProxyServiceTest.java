package se.yg.test.apiserver.health.trainer;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TrainerProxyServiceTest {

    @Autowired
    TrainerProxyService service;

    @Test
    void getTrainer(){
        TrainerDto trainerDto = service.getTrainer(3L).block();
        System.out.println(trainerDto);
    }

    @Test
    void postTrainer(){
        Set<String> role = new HashSet<>();
        role.add("NORMAL");
        role.add("MANAGER");
        role.add("HEAD");
        List<String> memberNameList = new ArrayList<>();
        memberNameList.add("memberName ... 1");
        memberNameList.add("memberName ... 2");
        memberNameList.add("memberName ... 3");
        memberNameList.add("memberName ... 4");
        memberNameList.add("memberName ... 5");
        TrainerDto trainerDTO = TrainerDto.builder()
                .name("name...")
                .age(19)
                .roleSet(role)
                .memberNameList(memberNameList)
                .uuid(UUID.randomUUID().toString())
                .build();



        Long id = service.addTrainer(trainerDTO).block();
        System.out.println(id);
    }


    @Test
    void getTrainer2(){
        ResponseEntity<String> rEntity = service.getTrainer1(6L).block();
        System.out.println(rEntity);

    }

    @Test
    void getTrainerList(){
        Flux<TrainerDto> obj = service.getTrainerList();
        System.out.println(obj);


    }


}