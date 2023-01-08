package rbekyarov.project.service;

import rbekyarov.project.models.dto.ReservationDTO;
import rbekyarov.project.models.dto.ReservationEditDTO;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.enums.Invoiced;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public interface ReservationService {
    List<Reservation> findAllReservationById();



    void addReservation(ReservationDTO reservationDTO, HttpSession session);

    void removeReservationById(Long id);

    Optional<Reservation> findById(Long id);

    void editReservation(Long id , ReservationEditDTO reservationEditDTO, HttpSession session);

    void setCellEmptyByReservationID(Long id);

    void statusReservationsUpdateAndStatusCellsUpdateEverytimeTableReservationUpdateOrCall();
    void changeInvoicedStatus(Long id, Invoiced invoiced);
}