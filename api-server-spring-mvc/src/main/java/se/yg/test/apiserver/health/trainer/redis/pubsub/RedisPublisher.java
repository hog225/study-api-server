package se.yg.test.apiserver.health.trainer.redis.pubsub;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import se.yg.test.apiserver.health.trainer.redis.entity.Trainer;

@Component
@RequiredArgsConstructor
public class RedisPublisher {

    @Value("${redis.channel}")
    private String channel;

    private final RedisTemplate redisTemplate;

    void publishData(Trainer trainer){
        redisTemplate.convertAndSend(channel, trainer);
    }

}
