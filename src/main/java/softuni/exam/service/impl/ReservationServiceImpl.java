package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.Dog;
import softuni.exam.models.entity.Price;
import softuni.exam.models.entity.Reservation;
import softuni.exam.models.entity.enums.*;
import softuni.exam.repository.CellRepository;
import softuni.exam.repository.ReservationRepository;
import softuni.exam.service.CellService;
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
    private final CellService cellService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, PriceService priceService, ModelMapper modelMapper, CellService cellService) {
        this.reservationRepository = reservationRepository;
        this.priceService = priceService;
        this.modelMapper = modelMapper;
        this.cellService = cellService;
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
        String substring1 = startDate.substring(0, 10);
        String substring2 = endDate.substring(0, 10);
        LocalDate date1 = formatterLocal(substring1);
        LocalDate date2 = formatterLocal(substring2);


        long countOvernightStay = ChronoUnit.DAYS.between(date1, date2);

        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));
        Price currentPrice = allPrices.get();

        Double price = 0.00;
        priceService.findAllPriceById();


        price += (int) countOvernightStay  * currentPrice.getPriceOvernightStay().doubleValue();

        if (reservationDTO.getFood().name().equals("YES")) {
            price += (int) countOvernightStay * currentPrice.getPriceFood().doubleValue();
        }
        if (reservationDTO.getTraining().name().equals("YES")) {
            price += currentPrice.getPriceTraining().doubleValue();
        }
        if (reservationDTO.getBathing().name().equals("YES")) {
            price += currentPrice.getPriceBathing().doubleValue();
        }
        if (reservationDTO.getCombing().name().equals("YES")) {
            price += currentPrice.getPriceCombing().doubleValue();
        }
        if (reservationDTO.getEars().name().equals("YES")) {
            price += currentPrice.getPriceEars().doubleValue();
        }
        if (reservationDTO.getPaws().name().equals("YES")) {
            price += currentPrice.getPricePaws().doubleValue();
        }
        if (reservationDTO.getNails().name().equals("YES")) {
            price += currentPrice.getPriceNails().doubleValue();
        }
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        double totalPrice = 0.0;
        Double discount = reservationDTO.getDiscount();
        if(discount!=null){
            totalPrice = price -(price*discount/100);
        }else {
            totalPrice = price;
            discount=0.0;
        }
        Cell cellCurrent = reservationDTO.getCell();
        reservation.setCell(cellCurrent);
        reservation.setStartDate(date1);
        reservation.setEndDate(date2);
        reservation.setDiscount(discount);
        reservation.setClient(reservationDTO.getClient());
        reservation.setDog(reservationDTO.getDog());
        reservation.setCountOvernightStay((int)countOvernightStay);
        reservation.setTotalPrice(new BigDecimal(totalPrice));
        reservation.setPrice(new BigDecimal(price));
        Long id = cellCurrent.getId();
        cellService.setCellBusy(id);

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
    LocalDate formatterLocal(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return localDate;
    }
}
