package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.BehaviorRepository;
import rbekyarov.project.service.BehaviorService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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

        // set dateCreated
        behavior.setDateCreate(LocalDate.now());

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
        //Edit Author
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        behaviorRepository.editBehavior(name, id,editAuthorId, dateEdit);


    }

}