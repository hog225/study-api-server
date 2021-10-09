package se.yg.test.apiserver.health.trainer.redis.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${redis.password}")
    private String redisPassword;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory(){

        //return new LettuceConnectionFactory("localhost", 6379);
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                //.readFrom(REPLICA_PREFERRED)
//                .
//                .build();

        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        serverConfig.setPassword(redisPassword);
        return new LettuceConnectionFactory(serverConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory());
        // 아래처럼 지정해야 Key 가 스트링으로 시리얼라즈 된다. 그렇지 않으면 이상한 문자로 시리얼라이즈 되서 Java 단에서 처리가 필요
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(lettuceConnectionFactory());
        // explicitly enable transaction support
        template.setEnableTransactionSupport(true);
        return template;
    }

}
