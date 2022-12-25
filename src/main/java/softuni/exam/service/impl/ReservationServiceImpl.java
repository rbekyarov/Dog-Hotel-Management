package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.Dog;
import softuni.exam.models.entity.Reservation;
import softuni.exam.models.entity.enums.*;
import softuni.exam.repository.ReservationRepository;
import softuni.exam.service.PriceService;
import softuni.exam.service.ReservationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final PriceService priceService;
    private final ModelMapper modelMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, PriceService priceService, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.priceService = priceService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Reservation> findAllReservationById() {
        return reservationRepository.findAllReservationById();
    }

    @Override
    public void addReservation(ReservationDTO reservationDTO) {
        // count date calculate
        String startDate = reservationDTO.getStartDate();
        String endDate = reservationDTO.getEndDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate date1 = LocalDate.parse(startDate, formatter);
        LocalDate date2 = LocalDate.parse(endDate, formatter);

        long countOvernightStay = ChronoUnit.DAYS.between(date1, date2);


        Double price = 0.00;
        //
        price += (int) countOvernightStay * priceService.getOvernightStayCurrentPrice();

        if (reservationDTO.getBathing().name().equals("YES")) {
            price += (int) countOvernightStay * priceService.getFoodCurrentPrice();
        }
        if (reservationDTO.getBathing().name().equals("YES")) {
            price += priceService.getBathingCurrentPrice();
        }
        if (reservationDTO.getBathing().name().equals("YES")) {
            price += priceService.getTrainingCurrentPrice();
        }
        if (reservationDTO.getBathing().name().equals("YES")) {
            price += priceService.getCombingCurrentPrice();
        }
        if (reservationDTO.getBathing().name().equals("YES")) {
            price += priceService.getEarsCurrentPrice();
        }
        if (reservationDTO.getBathing().name().equals("YES")) {
            price += priceService.getPawsCurrentPrice();
        }
        if (reservationDTO.getBathing().name().equals("YES")) {
            price += priceService.getNailsCurrentPrice();
        }
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        double totalPrice = 0.0;
        totalPrice = price -(price*reservationDTO.getDiscount());

        reservation.setCountOvernightStay((int)countOvernightStay);
        reservation.setTotalPrice(new BigDecimal(totalPrice));
        reservation.setPrice(new BigDecimal(price));

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
    public void editReservation(Long clientId, Long dogId, LocalDate startDate, LocalDate endDate,Integer countOvernightStay, Long cellId, Food food, Training training, Bathing bathing, Combing combing, Ears ears, Paws paws, Nails nails,BigDecimal price, Double discount,BigDecimal totalPrice, Long id) {
        reservationRepository.editReservation(clientId,
                dogId,
                startDate,
                endDate,
                countOvernightStay,
                cellId,
                food,
                training,
                bathing,
                combing,
                ears,
                paws,
                nails,
                price,
                discount,
                totalPrice,
                id);
    }
}
