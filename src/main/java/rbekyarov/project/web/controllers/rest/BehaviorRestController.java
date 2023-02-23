package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rbekyarov.project.models.dto.restDto.BehaviorRestDTO;
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
    public ResponseEntity<List<BehaviorRestDTO>> getAllBehavior() {
        return ResponseEntity.
                ok(behaviorService.findAllBehaviorForRest());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<BehaviorRestDTO> deleteBehaviorById(@PathVariable("id") Long id) {
//        behaviorService.deleteByIdForRest(id);
//
//        return ResponseEntity.
//                noContent().
//                build();
//    }
//    @PostMapping()
//    public ResponseEntity<BehaviorRestDTO> createBehavior(@RequestBody BehaviorRestDTO behaviorRestDTO,
//                                              UriComponentsBuilder uriComponentsBuilder) {
//
//        behaviorService.createBehaviorForRest(behaviorRestDTO);
//
//        return ResponseEntity.created(uriComponentsBuilder.
//                        path("/api/behaviors/{id}").build(behaviorRestDTO)).
//                build();
//    }
}
