package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbekyarov.project.models.dto.restDto.PriceRestDTO;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceRestController {
    private final InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoice() {
        return ResponseEntity.
                ok(invoiceService.getAllInvoiceForRest());
    }

}
