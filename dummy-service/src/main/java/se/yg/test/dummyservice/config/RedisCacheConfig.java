package se.yg.test.dummyservice.config;

import org.springframework.context.annotation.Configuration;


@Configuration
//@ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "redis", matchIfMissing = true)
public class RedisCacheConfig {


//    private final RedisConnectionFactory connectionFactory;
//    @Bean
//    public CacheManager redisCacheManager() {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
//                .fromConnectionFactory(connectionFactory).cacheDefaults(redisCacheConfiguration).build();
//        return redisCacheManager;
//    }
//    @Bean
//    public RedisCacheConfiguration cacheConfiguration(){
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(60))
//                .disableCachingNullValues()
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }
//
//
//    @Bean
//    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(){
//        return ((builder) -> builder
//                .withCacheConfiguration("itemCache",
//                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
//                .withCacheConfiguration("customCache",
//                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
//        );
//    }
}
