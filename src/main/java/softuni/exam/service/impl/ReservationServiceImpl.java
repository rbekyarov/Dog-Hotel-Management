package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.Dog;
import softuni.exam.models.entity.Reservation;
import softuni.exam.models.entity.enums.*;
import softuni.exam.repository.ReservationRepository;
import softuni.exam.service.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Reservation> findAllReservationById() {
        return reservationRepository.findAllReservationById();
    }

    @Override
    public void addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);

        reservationRepository.save(reservation);
    }

    @Override
    public void removeReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void editReservation(Long clientId, Set<Dog> dogs, LocalDate startDate, LocalDate endDate, Set<Cell> cells, Food food, Training training, Bathing bathing, Combing combing, Ears ears, Paws paws, Nails nails, Double discount, Long id) {
        reservationRepository.editReservation(clientId,
                dogs,
                startDate,
                endDate,
                cells,
                food,
                training,
                bathing,
                combing,
                ears,
                paws,
                nails,
                discount,
                id);
    }
}
