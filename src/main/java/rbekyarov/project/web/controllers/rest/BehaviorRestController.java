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

}
