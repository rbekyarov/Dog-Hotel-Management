package softuni.exam.service;

import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.*;
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
                         Set<Dog> dogs,
                         LocalDate startDate,
                         LocalDate endDate,
                         Set<Cell> cells,
                         Food food,
                         Training training,
                         Bathing bathing,
                         Combing combing,
                         Ears ears,
                         Paws paws,
                         Nails nails,
                         Double discount,
                 Long id);

}
