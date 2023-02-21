package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.BehaviorRepository;
import rbekyarov.project.service.BehaviorService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
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

    public Page<Behavior> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Behavior> list;
        List<Behavior> behaviors = behaviorRepository.findAllOrderById();
        if (behaviors.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, behaviors.size());
            list = behaviors.subList(startItem, toIndex);
        }

        Page<Behavior> behaviorsPage = new PageImpl<Behavior>(list, PageRequest.of(currentPage, pageSize), behaviors.size());

        return behaviorsPage;

}

    @Override
    public List<BehaviorDTO> findAllBehaviorForRest() {
        return behaviorRepository.findAll().
                stream().
                map(this::map).
                toList();
    }

    @Override
    public void deleteByIdForRest(Long id) {

        behaviorRepository.deleteById(id);

    }

    @Override
    public long createBehaviorForRest(BehaviorDTO behaviorDTO) {

        Behavior behavior = new Behavior().
                setName(behaviorDTO.getName());

        return behaviorRepository.save(behavior).getId();
    }


    private BehaviorDTO map(Behavior behavior) {

        return  new BehaviorDTO().
               setName(behavior.getName()).setId(behavior.getId());
    }
}