package se.yg.test.dummyservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yg.test.dummyservice.entity.TrainerClass;
import se.yg.test.dummyservice.repositories.MemberRepository;
import se.yg.test.dummyservice.repositories.TrainerRepository;
import se.yg.test.dummyservice.dto.TrainerDTO;
import se.yg.test.dummyservice.entity.Member;
import se.yg.test.dummyservice.entity.Trainer;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TrainerServiceImpl implements TrainerService{

    private final TrainerRepository trainerRepository;
    private final MemberRepository memberRepository;

    private Trainer dtoToEntity(TrainerDTO dto){
        Trainer trainer = Trainer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .age(dto.getAge())
                .roleSet(dto.getRoleSet())
                .uuid(dto.getUuid()).build();
        return trainer;
    }

    private TrainerDTO entityToDto(Trainer trainer, List<Member> members){

        List<String> memberNameList = members.stream().map(member -> {
            if (member != null)
                return member.getName();
            else
                return null;
        }).collect(Collectors.toList());

        TrainerDTO trainerDTO = TrainerDTO.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .age(trainer.getAge())
                .roleSet(trainer.getRoleSet())
                .memberNameList(memberNameList)
                .uuid(trainer.getUuid())
                .regDate(trainer.getRegDate())
                .modDate(trainer.getModDate())
                .build();

        return trainerDTO;
    }

    @Transactional
    @Override
    public Long addTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = dtoToEntity(trainerDTO);
        List<String> memberNames = trainerDTO.getMemberNameList();

        trainerRepository.save(trainer);

        // Trainer 서비스 에서 Member 를 추가 하는게 이상하나.. Test Service 임으로 그냥 ..
        for (String memberName : memberNames){

            Member member = Member.builder()
                    .name(memberName)
                    .trainer(trainer)
                    .build();
            memberRepository.save(member);
        }

        return trainer.getId();
    }

    @Override
    public TrainerDTO getTrainer(Long id) {

        List<Object[]> res = trainerRepository.getTrainerWithMember(id);
        if (res.size() == 0)
            return new TrainerDTO();

        Trainer t = (Trainer) res.get(0)[0];
        List<Member> members = new ArrayList<>();
        res.forEach(arr -> {
            Member member = (Member)arr[1];
            if (member != null)
                members.add(member);
        });

        return entityToDto(t, members);

    }

    @Override
    public Page<TrainerDTO> getTrainerList(Pageable page) {

        Page<TrainerDTO> ptrainer =  trainerRepository.findAllTrainerWithMember(page);
        List<Long> trainerIds = ptrainer.stream().map(o->{
            return o.getId();
        }).collect(Collectors.toList());

        Map<Long, List<Member>> members = memberRepository.findMemberNamesByTrainerId(trainerIds).stream()
                .collect(Collectors.groupingBy(o->o.getTrainer().getId()));

        ptrainer.forEach(trainer -> {
            List<String> memberNames  = members.get(trainer.getId()).stream().map(member->{
               return member.getName();
            }).collect(Collectors.toList());
            Set<TrainerClass> trainerClasses = new HashSet<TrainerClass>();
            List<Set<TrainerClass>> tClass = trainerRepository.getTrainerWithRoleSetByTrainerId(trainer.getId()).stream()
                    .map(t->t.getRoleSet()).collect(Collectors.toList());
            trainer.setMemberNameList(memberNames);
            trainer.setRoleSet(tClass.get(0));
        });

        return ptrainer;
    }

    @Override
    public List<TrainerDTO> getTrainerList(int size) {

        Pageable page = PageRequest.of(0, size, Sort.by(Sort.Direction.ASC, "id"));
        List<TrainerDTO> ptrainer =  trainerRepository.findAllTrainerWithMemberUsingList(page);
        List<Long> trainerIds = ptrainer.stream().map(o->{
            return o.getId();
        }).collect(Collectors.toList());

        Map<Long, List<Member>> members = memberRepository.findMemberNamesByTrainerId(trainerIds).stream()
                .collect(Collectors.groupingBy(o->o.getTrainer().getId()));

        ptrainer.forEach(trainer -> {
            List<String> memberNames  = members.get(trainer.getId()).stream().map(member->{
                return member.getName();
            }).collect(Collectors.toList());
            Set<TrainerClass> trainerClasses = new HashSet<TrainerClass>();
            List<Set<TrainerClass>> tClass = trainerRepository.getTrainerWithRoleSetByTrainerId(trainer.getId()).stream()
                    .map(t->t.getRoleSet()).collect(Collectors.toList());
            trainer.setMemberNameList(memberNames);
            trainer.setRoleSet(tClass.get(0));
        });

        return ptrainer;
    }

    @Transactional
    @Override
    public Long deleteTrainer(Long id) {
        memberRepository.deleteByTrainerId(id);
        trainerRepository.deleteById(id);
        return id;

    }
}
