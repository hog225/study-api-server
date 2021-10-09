package se.yg.test.apiserver.health.trainer.redis.pipline;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class PipeLine {

    private final RedisTemplate redisTemplate;

    void putData(){
        List<String> vals = Arrays.asList("aa", "bb", "cc");
        redisTemplate.opsForList().leftPushAll("pipeline-list", vals);
    }

    List<Object> readDataPipeline(){
        List<Object> results = redisTemplate.executePipelined(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for(int i = 0; i < 5; i++) {
                    connection.rPop("pipeline-list".getBytes(StandardCharsets.UTF_8));
                }
                return null;
            }
        });
        return results;
    }


    void putDataPipeline(){
        RedisSerializer keySerializer = redisTemplate.getStringSerializer();
        RedisSerializer valueSerializer = redisTemplate.getValueSerializer();

        redisTemplate.executePipelined((RedisCallback<Object>) RedisConnection -> {
            IntStream.range(1, 100).forEach(num -> {
                RedisConnection.rPush(keySerializer.serialize("Numbering"),
                        valueSerializer.serialize(num));
            });
            return null;
        });
    }

}
