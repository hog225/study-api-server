package se.yg.test.apiserver.health.trainer.redis.pipline;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import se.yg.test.apiserver.health.trainer.redis.entity.Trainer;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PipeLineTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    PipeLine pipeLine;

    @Test
    void put(){
        pipeLine.putData();
    }


    @Test
    void pipelinTest(){
        pipeLine.readDataPipeline().stream().forEach(object -> {
            System.out.println(object);
        });
    }

    @Test
    void redisTemplatetest(){
        RedisSerializer valueSerializer = redisTemplate.getStringSerializer();
        Trainer trainer = Trainer.builder()
                .id(1L)
                .name("trainer 1")
                .roleSet(Trainer.RoleSet.HEAD)
                .uuid(UUID.randomUUID().toString())
                .age(23)
                .members(Lists.list("member1", "member2"))
                .build();
        //redisTemplate.opsForList().leftPushAll("pipeline-list", vals);
        redisTemplate.opsForHash().put(
                trainer.getId().toString(),
                trainer.getId().hashCode(),
                valueSerializer.serialize(trainer.toString())
        );
    }

}