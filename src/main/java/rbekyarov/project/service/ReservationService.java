package rbekyarov.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.ReservationDTO;
import rbekyarov.project.models.dto.ReservationEditDTO;
import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.enums.Invoiced;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public interface ReservationService {
    List<Reservation> findAllReservationByDesc();



    void addReservation(ReservationDTO reservationDTO, HttpSession session);

    void removeReservationById(Long id);

    Optional<Reservation> findById(Long id);

    void editReservation(Long id , ReservationEditDTO reservationEditDTO, HttpSession session);

    void setCellEmptyByReservationID(Long id);

    void statusReservationsUpdateAndStatusCellsUpdateEverytimeTableReservationUpdateOrCall();
    void changeInvoicedStatus(Long id, Invoiced invoiced);

    List<Reservation> listReservationByClientEmail(String clientEmail);
    Page<Reservation> findPaginated(Pageable pageable);

    List<Reservation> listReservationById(long parseLong);

    List<Reservation> findAllActiveReservation();
    List<Reservation> findAllActiveReservationLimit3();

    List<Dog> findActiveReservedDogs();

    List<Reservation> findAllUpcomingReservations();

    void statusCellsUpdateEmpty();

    int getCountActiveReservation();

    int getCountUpcomingReservation();

    int getCountCompletedReservation();
}
