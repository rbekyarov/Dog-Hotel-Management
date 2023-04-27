package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rbekyarov.project.models.dto.restDto.BehaviorRestDTO;
import rbekyarov.project.models.dto.restDto.CityRestDTO;
import rbekyarov.project.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityRestController {
    private final CityService cityService;

    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityRestDTO>> getAllCity() {
        return ResponseEntity.
                ok(cityService.findAllCityForRest());
    }

}
