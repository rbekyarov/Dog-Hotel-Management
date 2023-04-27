package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.dto.restDto.BehaviorRestDTO;
import rbekyarov.project.models.dto.restDto.BreedRestDTO;
import rbekyarov.project.service.BreedService;

import java.util.List;

@RestController
@RequestMapping("/api/breeds")
public class BreedRestController {
    private final BreedService breedService;

    public BreedRestController(BreedService breedService) {
        this.breedService = breedService;
    }


    @GetMapping
    public ResponseEntity<List<BreedRestDTO>> getAllBreed() {
        return ResponseEntity.
                ok(breedService.findAllBreedForRest());
    }

}
