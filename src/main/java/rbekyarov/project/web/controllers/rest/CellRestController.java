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

}
