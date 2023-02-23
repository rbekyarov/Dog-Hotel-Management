package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbekyarov.project.models.dto.restDto.PriceRestDTO;
import rbekyarov.project.models.dto.restDto.VendorRestDTO;
import rbekyarov.project.service.VendorService;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorRestController {
    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @GetMapping
    public ResponseEntity<List<VendorRestDTO>> getAllVendors() {
        return ResponseEntity.
                ok(vendorService.getAllVendorForRest());
    }

}
