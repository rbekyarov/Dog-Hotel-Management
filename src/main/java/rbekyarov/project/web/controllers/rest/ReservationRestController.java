package rbekyarov.project.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservation() {
        return ResponseEntity.
                ok(reservationService.findAllReservationForRest());
    }
}
