package se.yg.test.dummyservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.yg.test.dummyservice.dto.TrainerDTO;
import se.yg.test.dummyservice.entity.Trainer;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    //@EntityGraph(attributePaths = {"roleSet"}, type=EntityGraph.EntityGraphType.LOAD)
    @Query("select t, m from Trainer t left outer join Member m on m.trainer = t " +
            "where t.id = :id ")
    List<Object[]> getTrainerWithMember(@Param("id") Long id);

    @Query("select new se.yg.test.dummyservice.dto.TrainerDTO(t.id, t.name, t.age, t.uuid, t.regDate, t.modDate) " +
            "from Trainer t ")
    Page<TrainerDTO> findAllTrainerWithMember(Pageable pageable);

    @Query("select new se.yg.test.dummyservice.dto.TrainerDTO(t.id, t.name, t.age, t.uuid, t.regDate, t.modDate) " +
            "from Trainer t ")
    List<TrainerDTO> findAllTrainerWithMemberUsingList(Pageable pageable);
//    @Query("select tc from se.yg.test.dummyservice.entity.TrainerClass tc where tc.trainer.id = :tid")
//    List<TrainerClass> findroleSetByTrainerId(@Param("tid") tid);

    @EntityGraph(attributePaths = {"roleSet"}, type=EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Trainer t where t.id = :id")
    List<Trainer> getTrainerWithRoleSetByTrainerId(@Param("id") Long id);


}
