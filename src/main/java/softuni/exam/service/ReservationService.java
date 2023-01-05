package softuni.exam.service;

import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.dto.ReservationEditDTO;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface ReservationService {
    List<Reservation> findAllReservationById();



    void addReservation(ReservationDTO reservationDTO);

    void removeReservationById(Long id);

    Optional<Reservation> findById(Long id);

    void editReservation(Long id , ReservationEditDTO reservationEditDTO);

    void setCellEmptyByReservationID(Long id);

    void statusReservationsUpdateAndStatusCellsUpdateEverytimeTableReservationUpdateOrCall();
}
