package se.yg.test.dummyservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import se.yg.test.dummyservice.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {

    Long addTrainer(TrainerDTO trainerDTO);

    TrainerDTO getTrainer(Long id);

    Page<TrainerDTO> getTrainerList(Pageable page);

    List<TrainerDTO> getTrainerList(int size);

    Long deleteTrainer(Long id);





}
