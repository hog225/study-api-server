package se.yg.test.dummyservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import se.yg.test.dummyservice.entity.TrainerClass;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@RedisHash("TrainerDTO")
public class TrainerDTO implements Serializable {
    private Long id;
    private String name;
    private int age;
    @Builder.Default
    private Set<TrainerClass> roleSet= new HashSet<>();
    @Builder.Default
    private List<String> memberNameList = new ArrayList<>();
    private String uuid;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime regDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime modDate;

    public TrainerDTO(Long id, String name, int age, String uuid, LocalDateTime regDate, LocalDateTime modDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.uuid = uuid;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
