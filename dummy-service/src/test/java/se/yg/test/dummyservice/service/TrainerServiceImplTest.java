package se.yg.test.dummyservice.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import se.yg.test.dummyservice.dto.TrainerDTO;
import se.yg.test.dummyservice.entity.Trainer;
import se.yg.test.dummyservice.entity.TrainerClass;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class TrainerServiceImplTest {
    @Autowired
    TrainerService trainerService;


    @Test
    public void beforeTask(){
        Set<TrainerClass> role = new HashSet<>();
        role.add(TrainerClass.NORMAL);
        role.add(TrainerClass.MANAGER);
        role.add(TrainerClass.HEAD);
        List<String> memberNameList = new ArrayList<>();

        IntStream.rangeClosed(0, 50).forEach(v-> {
            memberNameList.clear();
            memberNameList.add("memberName ... " + v);
            memberNameList.add("memberName2 ... " + v);
            TrainerDTO trainerDTO = TrainerDTO.builder()
                    .name("name." + v)
                    .age(19 + v)
                    .roleSet(role)
                    .memberNameList(memberNameList)
                    .uuid(UUID.randomUUID().toString())
                    .build();

            trainerService.addTrainer(trainerDTO);

        });



    }

    @Test
    public void saveTest(){
        Set<TrainerClass> role = new HashSet<>();
        role.add(TrainerClass.NORMAL);
        role.add(TrainerClass.MANAGER);
        role.add(TrainerClass.HEAD);
        List<String> memberNameList = new ArrayList<>();
        memberNameList.add("memberName ... 1");
        memberNameList.add("memberName ... 2");
        memberNameList.add("memberName ... 3");
        memberNameList.add("memberName ... 4");
        memberNameList.add("memberName ... 5");
        TrainerDTO trainerDTO = TrainerDTO.builder()
                .name("name...")
                .age(19)
                .roleSet(role)
                .memberNameList(memberNameList)
                .uuid(UUID.randomUUID().toString())
                .build();

        Long id = trainerService.addTrainer(trainerDTO);
        System.out.println(id);
        System.out.println(trainerService.getTrainer(id));
        System.out.println(trainerService.deleteTrainer(id));
        System.out.println(trainerService.getTrainer(id));

    }

    @Test
    public void getTrainerList(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        trainerService.getTrainerList(pageable).forEach(o-> {
            System.out.println(o);
        });

    }

    @Test
    public void saveTest_noRole(){
        List<String> memberNameList = new ArrayList<>();
        memberNameList.add("memberName ... 1");
        memberNameList.add("memberName ... 2");
        memberNameList.add("memberName ... 3");
        memberNameList.add("memberName ... 4");
        memberNameList.add("memberName ... 5");
        TrainerDTO trainerDTO = TrainerDTO.builder()
                .name("name...")
                .age(19)

                .memberNameList(memberNameList)
                .uuid(UUID.randomUUID().toString())
                .build();

        Long id = trainerService.addTrainer(trainerDTO);
        System.out.println(id);
        System.out.println(trainerService.getTrainer(id));

    }


    @Test
    public void saveTest_noRole_noMember(){
        TrainerDTO trainerDTO = TrainerDTO.builder()
                .name("name...")
                .age(19)
                .uuid(UUID.randomUUID().toString())
                .build();

        Long id = trainerService.addTrainer(trainerDTO);
        System.out.println(id);
        System.out.println(trainerService.getTrainer(id));

    }

    @Test
    public void redisRepositorySave(){
        Set<TrainerClass> role = new HashSet<>();
        role.add(TrainerClass.NORMAL);
        role.add(TrainerClass.MANAGER);
        role.add(TrainerClass.HEAD);
        List<String> memberNameList = new ArrayList<>();
        memberNameList.add("memberName ... 1");
        memberNameList.add("memberName ... 2");
        memberNameList.add("memberName ... 3");
        memberNameList.add("memberName ... 4");
        memberNameList.add("memberName ... 5");
        TrainerDTO trainerDTO = TrainerDTO.builder()
                .id(1L)
                .name("name...")
                .age(19)
                .roleSet(role)
                .memberNameList(memberNameList)
                .uuid(UUID.randomUUID().toString())
                .build();
        trainerService.addTrainerRedis(trainerDTO);
        trainerService.addTrainerRedis(trainerDTO);
        trainerService.addTrainerRedis(trainerDTO);
        trainerService.addTrainerRedis(trainerDTO);
    }




}