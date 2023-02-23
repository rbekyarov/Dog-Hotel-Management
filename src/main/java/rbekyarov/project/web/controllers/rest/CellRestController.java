package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rbekyarov.project.models.dto.restDto.CellRestDTO;
import rbekyarov.project.service.CellService;

import java.util.List;

@RestController
@RequestMapping("/api/cells")
public class CellRestController {
    private final CellService cellService;

    public CellRestController(CellService cellService) {
        this.cellService = cellService;
    }

    @GetMapping
    public ResponseEntity<List<CellRestDTO>> getAllCell() {
        return ResponseEntity.
                ok(cellService.findAllCellForRest());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<CellRestDTO> deleteCellById(@PathVariable("id") Long id) {
//        cellService.deleteByIdForRest(id);
//
//        return ResponseEntity.
//                noContent().
//                build();
//    }
//    @PostMapping()
//    public ResponseEntity<CellRestDTO> createCell(@RequestBody CellRestDTO cellRestDTO,
//                                              UriComponentsBuilder uriComponentsBuilder) {
//
//        cellService.createCellForRest(cellRestDTO);
//
//        return ResponseEntity.created(uriComponentsBuilder.
//                        path("/api/cells/{id}").build(cellRestDTO)).
//                build();
//    }
}
