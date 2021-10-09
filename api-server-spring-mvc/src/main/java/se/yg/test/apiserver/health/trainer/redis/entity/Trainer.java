package se.yg.test.apiserver.health.trainer.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash("Trainer")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Trainer implements Serializable {

    public enum RoleSet {
        NORMAL, MANAGER, HEAD
    }


    private Long id;
    private String name;
    private int age;
    private RoleSet roleSet;
    private String uuid;
    private List<String> members;
}
