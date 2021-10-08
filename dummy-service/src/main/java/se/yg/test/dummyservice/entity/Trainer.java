package se.yg.test.dummyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trainer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    // 지우기 전에도 Select 하고 좋지 않음
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="ROLE_SET", joinColumns = @JoinColumn(name = "TRAINER_ID"))
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<TrainerClass> roleSet = new HashSet<>();

    private String uuid;

    public void addTrainerRole(TrainerClass trainerClass){
        roleSet.add(trainerClass);
    }
}
