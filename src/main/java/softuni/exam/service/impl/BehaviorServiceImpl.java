package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BehaviorDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.User;
import softuni.exam.repository.BehaviorRepository;
import softuni.exam.service.BehaviorService;
import softuni.exam.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Service
public class BehaviorServiceImpl implements BehaviorService {
    private final BehaviorRepository behaviorRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public BehaviorServiceImpl(BehaviorRepository behaviorRepository, ModelMapper modelMapper, UserService userService) {
        this.behaviorRepository = behaviorRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<Behavior> findAllBehaviorById() {
        return behaviorRepository.findAllOrderById();
    }

    @Override
    public void addBehaviors(BehaviorDTO behaviorDTO, HttpSession session) {


        Behavior behavior = modelMapper.map(behaviorDTO, Behavior.class);
        behavior.setName(behaviorDTO.getName());

        //get and set Author
        behavior.setAuthor(userService.getAuthorFromSession(session));


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
    public void editBehaviors(String name, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        behaviorRepository.editBehavior(name, id,editAuthorId);


    }

}
