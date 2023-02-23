package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbekyarov.project.models.dto.restDto.PriceRestDTO;
import rbekyarov.project.service.PriceService;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PricesRestController {
    private final PriceService priceService;

    public PricesRestController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<List<PriceRestDTO>> getAllPrices() {
        return ResponseEntity.
                ok(priceService.getAllPricesForRest());
    }

}
