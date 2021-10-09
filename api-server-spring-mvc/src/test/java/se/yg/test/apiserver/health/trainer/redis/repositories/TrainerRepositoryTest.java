package se.yg.test.apiserver.health.trainer.redis.repositories;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.yg.test.apiserver.health.trainer.redis.entity.Trainer;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrainerRepositoryTest {

    @Autowired
    TrainerRepository trainerRepository;

    @Test
    void redisCreateTest(){
        Trainer trainer = Trainer.builder()
                .id(1L)
                .name("trainer 1")
                .roleSet(Trainer.RoleSet.HEAD)
                .uuid(UUID.randomUUID().toString())
                .age(23)
                .members(Lists.list("member1", "member2"))
                .build();
        trainerRepository.save(trainer);
    }

    @Test
    void redisReadTest(){
        Trainer trainer = trainerRepository.findById(1L).get();
        assertEquals(1L, trainer.getId());
        System.out.println(trainer);
    }

    @Test
    void redisDeleteTest(){
        Trainer trainer = trainerRepository.findById(1L).get();
        trainerRepository.deleteById(trainer.getId());
    }


}