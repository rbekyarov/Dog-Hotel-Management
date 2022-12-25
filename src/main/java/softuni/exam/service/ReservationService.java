package softuni.exam.service;

import softuni.exam.models.dto.ReservationDTO;
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

    void editReservation(Long clientId,
                         Long dogId,
                         LocalDate startDate,
                         LocalDate endDate,
                         Integer countOvernightStay,
                         Long cellId,
                         Food food,
                         Training training,
                         Bathing bathing,
                         Combing combing,
                         Ears ears,
                         Paws paws,
                         Nails nails,
                         BigDecimal price,
                         Double discount,
                         BigDecimal totalPrice,
                 Long id);

}
