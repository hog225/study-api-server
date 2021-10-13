package se.yg.test.dummyservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ActiveProfiles;
import se.yg.test.dummyservice.config.RedisConfig;

import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

@ActiveProfiles("local")
@SpringBootTest(classes = {RedisConfig.class})
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void redisTemplateTest(){
        String key = "redisListTest";
        String keyforhash = "redisHashTest";
        ListOperations<String, String> op = redisTemplate.opsForList();
        HashOperations<String, String, String> hop = redisTemplate.opsForHash();


        op.rightPush(key, "this ");
        op.rightPush(key, "is ");
        op.rightPush(key, "sparta ");
        // LRANGE redisListTest 0 10

        hop.put(keyforhash, "HashKey1", "HashVal1");
        hop.put(keyforhash, "HashKey2", "HashVal2");
        hop.put(keyforhash, "HashKey3", "HashVal3");
        hop.put(keyforhash, "HashKey4", "HashVal4");
        //

    }

    @Test
    public void redisGetTemplateTest(){
        String key = "redisTest1";
        String keyforhash = "redisHashTest";
        ListOperations<String, String> op = redisTemplate.opsForList();
        HashOperations<String, String, String> hop = redisTemplate.opsForHash();

        System.out.println(op.leftPop(key));
        System.out.println(op.leftPop(key));
        System.out.println(op.leftPop(key));

        System.out.println(hop.get(keyforhash, "HashKey1"));

    }

    @Test
    public void redisPipeline(){
        RedisSerializer keySerializer = redisTemplate.getStringSerializer();
        RedisSerializer valueSerializer = redisTemplate.getValueSerializer();

        redisTemplate.executePipelined((RedisCallback<String>) RedisConnection -> {
            IntStream.range(1, 100).forEach(num -> {
                RedisConnection.rPush(
                        keySerializer.serialize("numbering"),
                        valueSerializer.serialize(num)
                );
            });
            return null;
        });
    }
    @Test
    public void keysTest(){
        Set<String> keys = redisTemplate.keys("redis*");
        System.out.println(keys);

        ValueOperations<String, Long> RedisVal= redisTemplate.opsForValue();

        System.out.println(RedisVal.get("KeyTrainerDto") + " ---");

    }


}
