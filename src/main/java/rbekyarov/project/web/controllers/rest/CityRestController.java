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

//    @DeleteMapping("/{id}")
//    public ResponseEntity<CityRestDTO> deleteCityById(@PathVariable("id") Long id) {
//        cityService.deleteByIdForRest(id);
//
//        return ResponseEntity.
//                noContent().
//                build();
//    }
//    @PostMapping()
//    public ResponseEntity<CityRestDTO> createCity(@RequestBody CityRestDTO cityRestDTO,
//                                              UriComponentsBuilder uriComponentsBuilder) {
//
//        cityService.createCityForRest(cityRestDTO);
//
//        return ResponseEntity.created(uriComponentsBuilder.
//                        path("/api/cities/{id}").build(cityRestDTO)).
//                build();
//    }
}
