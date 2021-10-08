package se.yg.test.apiserver.health.trainer;

import lombok.*;

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
public class TrainerDto {
    private Long id;
    private String name;
    private int age;
    @Builder.Default
    private Set<String> roleSet= new HashSet<>();
    @Builder.Default
    private List<String> memberNameList = new ArrayList<>();
    private String uuid;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public TrainerDto(Long id, String name, int age, String uuid, LocalDateTime regDate, LocalDateTime modDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.uuid = uuid;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
