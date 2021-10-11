package se.yg.test.apiserver.health.trainer.redis.pubsub;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import se.yg.test.apiserver.health.trainer.redis.entity.Trainer;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Log4j2
public class RedisSubscriber{

    @Value("${redis.channel}")
    private String channel;

    private final LettuceConnectionFactory lettuceConnectionFactory;


    void subscribeData(){
        RedisConnection conn = lettuceConnectionFactory.getConnection();
        conn.subscribe(new CustomMessageListener(), channel.getBytes(StandardCharsets.UTF_8));
    }



}
