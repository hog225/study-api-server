package se.yg.test.dummyservice.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.yg.test.dummyservice.entity.Trainer;
import se.yg.test.dummyservice.entity.TrainerClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
class TrainerRepositoryTest {

    @Autowired
    TrainerRepository trainerRepository;

    @BeforeEach
    void setData(){
        Set<TrainerClass> role = new HashSet<>();
        role.add(TrainerClass.NORMAL);
        role.add(TrainerClass.MANAGER);
        role.add(TrainerClass.HEAD);
        Trainer trainer = Trainer.builder()
                .name("name...")
                .age(19)
                .roleSet(role)
                .uuid(UUID.randomUUID().toString())
                .build();

        trainerRepository.save(trainer);
    }

    @Test
    void testGetTrainerWithMember(){
        System.out.println(trainerRepository.getTrainerWithMember(1L));
    }

    @Test
    void testRoleSet(){
        List<Trainer> tClass = trainerRepository.getTrainerWithRoleSetByTrainerId(4L);
        tClass.stream().forEach(t -> {
            System.out.println(t.getRoleSet());
        });
    }


}