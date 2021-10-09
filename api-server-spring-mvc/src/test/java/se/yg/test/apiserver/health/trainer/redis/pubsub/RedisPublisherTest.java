package se.yg.test.apiserver.health.trainer.redis.pubsub;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.yg.test.apiserver.health.trainer.redis.entity.Trainer;

import java.util.UUID;

@SpringBootTest
class RedisPublisherTest {

    @Autowired
    RedisPublisher publisher;

    @Autowired
    RedisSubscriber subscriber;

    @Test
    void testPubSub(){
        Trainer trainer = Trainer.builder()
                .id(1L)
                .name("trainer 1")
                .roleSet(Trainer.RoleSet.HEAD)
                .uuid(UUID.randomUUID().toString())
                .age(23)
                .members(Lists.list("member1", "member2"))
                .build();
        publisher.publishData(trainer);
    }

    @Test
    void testRedisSubscribe(){
        subscriber.subscribeData();
    }

}