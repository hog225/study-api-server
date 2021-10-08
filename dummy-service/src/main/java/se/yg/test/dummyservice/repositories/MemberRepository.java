package se.yg.test.dummyservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.yg.test.dummyservice.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Modifying
    @Query("delete from Member m where m.trainer.id = :tid")
    void deleteByTrainerId(@Param("tid") Long tid);

    @Query("select m.name from Member m where m.trainer.id = :tid")
    List<String> findMemberNamesByTrainerId(@Param("tid") Long tid);

    @Query("select m from Member m where m.trainer.id in :tid")
    List<Member> findMemberNamesByTrainerId(@Param("tid") List<Long> tid);



}
