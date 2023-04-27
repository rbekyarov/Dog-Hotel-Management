package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rbekyarov.project.models.dto.restDto.CityRestDTO;
import rbekyarov.project.models.dto.restDto.ClientRestDTO;
import rbekyarov.project.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public ResponseEntity<List<ClientRestDTO>> getAllClient() {
        return ResponseEntity.
                ok(clientService.findAllClientForRest());
    }

}
