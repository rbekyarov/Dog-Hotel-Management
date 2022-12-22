package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BehaviorDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.repository.BehaviorRepository;
import softuni.exam.service.BehaviorService;

import java.util.List;
import java.util.Optional;


@Service
public class BehaviorServiceImpl implements BehaviorService {
    private final BehaviorRepository behaviorRepository;
    private final ModelMapper modelMapper;


    public BehaviorServiceImpl(BehaviorRepository behaviorRepository, ModelMapper modelMapper) {
        this.behaviorRepository = behaviorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Behavior> findAllBehaviorById() {
        return behaviorRepository.findAllOrderById();
    }

    @Override
    public void addBehaviors(BehaviorDTO behaviorDTO) {


        Behavior behavior = modelMapper.map(behaviorDTO, Behavior.class);
        behavior.setName(behaviorDTO.getName());
        behaviorRepository.save(behavior);
    }

    @Override
    public void removeBehaviorById(Long id) {
        behaviorRepository.deleteById(id);
    }

    @Override
    public Optional<Behavior> findById(Long id) {
        return behaviorRepository.findById(id);
    }

    @Override
    public void editBehaviors(String name, Long id) {
        behaviorRepository.editBehavior(name, id);

    }

}
