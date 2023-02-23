package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbekyarov.project.models.dto.restDto.CostRestDTO;
import rbekyarov.project.models.dto.restDto.VendorRestDTO;
import rbekyarov.project.service.CostService;

import java.util.List;

@RestController
@RequestMapping("/api/costs")
public class CostRestController {
    private final CostService costService;

    public CostRestController(CostService costService) {
        this.costService = costService;
    }


    @GetMapping
    public ResponseEntity<List<CostRestDTO>> getAllVendors() {
        return ResponseEntity.
                ok(costService.getAllCostForRest());
    }

}
