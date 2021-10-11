package se.yg.test.apiserver.health.trainer.redis.pubsub;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Log4j2
public class CustomMessageListener implements MessageListener {


    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info(message.toString());
    }
}
