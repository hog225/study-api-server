package se.yg.test.dummyservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.yg.test.dummyservice.entity.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    void findByTrainerId(){
        List<String> members = repository.findMemberNamesByTrainerId(2L);
        System.out.println(members);

    }


}