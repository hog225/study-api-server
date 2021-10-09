package se.yg.test.apiserver.health.trainer.redis.pipline;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

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
    }

}