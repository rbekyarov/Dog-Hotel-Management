package softuni.exam.service;

import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.dto.ReservationEditDTO;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;


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
