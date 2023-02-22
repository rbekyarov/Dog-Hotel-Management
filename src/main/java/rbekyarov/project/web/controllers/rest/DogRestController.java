package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbekyarov.project.models.dto.restDto.ClientRestDTO;
import rbekyarov.project.models.dto.restDto.DogRestDTO;
import rbekyarov.project.service.DogService;

import java.util.List;

@RestController
@RequestMapping("/api/dogs")
public class DogRestController {
    private final DogService dogService;

    public DogRestController(DogService dogService) {
        this.dogService = dogService;
    }


    @GetMapping
    public ResponseEntity<List<DogRestDTO>> getAllDog() {
        return ResponseEntity.
                ok(dogService.findAllDogForRest());
    }


}
