package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.service.BehaviorService;

import java.util.List;

@RestController
@RequestMapping("/api/behaviors")
public class BehaviorRestController {
    private final BehaviorService behaviorService;

    public BehaviorRestController(BehaviorService behaviorService) {
        this.behaviorService = behaviorService;
    }
    @GetMapping
    public ResponseEntity<List<BehaviorDTO>> getAllBehavior() {
        return ResponseEntity.
                ok(behaviorService.findAllBehaviorForRest());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BehaviorDTO> deleteBehaviorById(@PathVariable("id") Long id) {
        behaviorService.deleteByIdForRest(id);

        return ResponseEntity.
                noContent().
                build();
    }
    @PostMapping()
    public ResponseEntity<BehaviorDTO> createBehavior(@RequestBody BehaviorDTO behaviorDTO,
                                              UriComponentsBuilder uriComponentsBuilder) {
        long newBehaviorId = behaviorService.createBehaviorForRest(behaviorDTO);

        return ResponseEntity.created(uriComponentsBuilder.
                        path("/api/behaviors/{id}").build(behaviorDTO)).
                build();
    }
}
