package se.yg.test.dummyservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yg.test.dummyservice.aop.ExecutionTime;
import se.yg.test.dummyservice.dto.TrainerDTO;
import se.yg.test.dummyservice.service.TrainerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@Log4j2
public class TrainerController {

    private final TrainerService trainerService;
    @ExecutionTime
    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<TrainerDTO> getTrainer(@PathVariable("trainerId") Long trainerId){
        TrainerDTO tdto = trainerService.getTrainer(trainerId);
        return new ResponseEntity<>(tdto, HttpStatus.OK);
    }

    @ExecutionTime
    @GetMapping("/trainer-list")
    public List<TrainerDTO> getTrainers(
            //@RequestParam(value = "page") int page
            @RequestParam(value = "size") int size

    ){

        List<TrainerDTO> tdto = trainerService.getTrainerList(size);
        return tdto;
    }

    @ExecutionTime
    @GetMapping("/trainer-page")
    public Page<TrainerDTO> getTrainers(
            @PageableDefault(page =0, size= 10, direction = Sort.Direction.ASC, sort="id") Pageable pageable
    ){

        Page<TrainerDTO> tdto = trainerService.getTrainerList(pageable);
        return tdto;
    }
    @ExecutionTime
    @PostMapping(value = "/trainer", consumes = "application/json")
    public Long addTrainer(@RequestBody TrainerDTO body){


        if (body.getUuid() == null)
            body.setUuid(UUID.randomUUID().toString());

        Long tid = trainerService.addTrainer(body);

        return tid;
    }

    @ExecutionTime
    @DeleteMapping("/trainer/{trainerId}")
    public Long delTrainer(@PathVariable("trainerId") Long trainerId){
        try{
            Long tid = trainerService.deleteTrainer(trainerId);
            return tid;

        } catch (Exception e){
            return trainerId;
        }
    }


}
