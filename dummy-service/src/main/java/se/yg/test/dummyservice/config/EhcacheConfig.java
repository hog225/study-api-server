package se.yg.test.dummyservice.config;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.yg.test.dummyservice.dto.TrainerDTO;

import java.time.Duration;

@Configuration
public class EhcacheConfig {


    public static final String DB_CACHE = "db_cache";
    public static final String TRAINERDTO_CACHE = "trainer_cache";


    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public EhcacheConfig() {
        this.jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(10000, EntryUnit.ENTRIES))
                .withSizeOfMaxObjectSize(1000, MemoryUnit.B)
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(300)))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(600))));
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(DB_CACHE, jcacheConfiguration);
            cm.createCache(TRAINERDTO_CACHE, Eh107Configuration.fromEhcacheCacheConfiguration(CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, TrainerDTO.class,
                    ResourcePoolsBuilder.newResourcePoolsBuilder()
                            .heap(10000, EntryUnit.ENTRIES))
                    .withSizeOfMaxObjectSize(1000, MemoryUnit.B)
                    .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20)))));
        };
    }


    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }
//    @Bean
//    public JCacheManagerCustomizer cacheManagerCustomizer() {
//        return cm -> {
//            cm.createCache(DB_CACHE, jcacheConfiguration);
//        };
//    }
}
