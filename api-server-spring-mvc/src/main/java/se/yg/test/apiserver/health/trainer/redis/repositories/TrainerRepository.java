package se.yg.test.apiserver.health.trainer.redis.repositories;

import org.springframework.data.repository.CrudRepository;
import se.yg.test.apiserver.health.trainer.redis.entity.Trainer;

public interface TrainerRepository extends CrudRepository<Trainer, Long> {
}
