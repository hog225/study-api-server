package se.yg.test.dummyservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.yg.test.dummyservice.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {

    Long addTrainer(TrainerDTO trainerDTO);

    TrainerDTO getTrainer(Long id);

    TrainerDTO getTrainerWrapper(Long id);

    Page<TrainerDTO> getTrainerList(Pageable page);

    List<TrainerDTO> getTrainerList(int size);

    Long deleteTrainer(Long id);

    Long addTrainerRedis(TrainerDTO trainerDTO);



}
