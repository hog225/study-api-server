package se.yg.test.dummyservice.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import se.yg.test.dummyservice.config.EhcacheConfig;
import se.yg.test.dummyservice.config.RedisCacheConfig;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "trainer")
//@Cacheable
//@org.hibernate.annotations.Cache(region = EhcacheConfig.DB_CACHE, usage = CacheConcurrencyStrategy.READ_ONLY)
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trainer trainer;
}
