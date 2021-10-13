package se.yg.test.dummyservice.repositories;

import org.springframework.data.repository.CrudRepository;
import se.yg.test.dummyservice.dto.TrainerDTO;

public interface TrainerDtoRepository extends CrudRepository<TrainerDTO, Long> {

}

