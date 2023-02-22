package rbekyarov.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.dto.restDto.BehaviorRestDTO;
import rbekyarov.project.models.entity.Behavior;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface BehaviorService {
    List<Behavior>findAllBehaviorById();

    void addBehaviors(BehaviorDTO behaviorDTO, HttpSession session);

    void removeBehaviorById(Long id);

    Optional<Behavior> findById(Long id);

    void editBehaviors(String name, Long id,HttpSession session);

    Page<Behavior> findPaginated(Pageable pageable);

    List<BehaviorRestDTO> findAllBehaviorForRest();

    void deleteByIdForRest(Long id);

    long createBehaviorForRest(BehaviorRestDTO behaviorRestDTO);
}
