package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.dto.ReservationEditDTO;
import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.Price;
import softuni.exam.models.entity.Reservation;
import softuni.exam.models.entity.User;
import softuni.exam.models.entity.enums.*;
import softuni.exam.repository.ReservationRepository;
import softuni.exam.service.CellService;
import softuni.exam.service.PriceService;
import softuni.exam.service.ReservationService;
import softuni.exam.service.UserService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final PriceService priceService;
    private final ModelMapper modelMapper;
    private final CellService cellService;
    private final UserService userService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, PriceService priceService, ModelMapper modelMapper, CellService cellService, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.priceService = priceService;
        this.modelMapper = modelMapper;
        this.cellService = cellService;
        this.userService = userService;
    }

    @Override
    public List<Reservation> findAllReservationById() {
        return reservationRepository.findAllReservationById();
    }

    @Override
    public void addReservation(ReservationDTO reservationDTO, HttpSession session) {
        // count date calculate

        String startDate = reservationDTO.getStartDate();
        String endDate = reservationDTO.getEndDate();
        String substring1 = startDate.substring(0, 10);
        String substring2 = endDate.substring(0, 10);
        LocalDate date1 = formatterLocal(substring1);
        LocalDate date2 = formatterLocal(substring2);

        //set statusReservation
        StatusReservation statusReservation = StatusReservation.noset;

        LocalDate dateNow = LocalDate.now();
        if((dateNow.isBefore(date1)) && (dateNow.isBefore(date2))){
            statusReservation = StatusReservation.upcoming;
        }else if ((dateNow.isAfter(date1))&&(dateNow.isEqual(date2))){
            statusReservation = StatusReservation.active;

        }else if((dateNow.isAfter(date1))&&(dateNow.isAfter(date2))){
            statusReservation = StatusReservation.completed;

        }


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
        reservation.setStatusReservation(statusReservation);

        Long id = cellCurrent.getId();
        if(statusReservation.name().equals("active")){
            cellService.setCellBusy(id);
        }
        //get and set Author
        reservation.setAuthor(userService.getAuthorFromSession(session));

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
    public void editReservation(Long reservationId, ReservationEditDTO reservationEditDTO, HttpSession session) {

        //Get new Date
        String startDate = reservationEditDTO.getStartDate();
        String endDate = reservationEditDTO.getEndDate();
        String substring1 = startDate.substring(0, 10);
        String substring2 = endDate.substring(0, 10);
        LocalDate date1 = formatterLocal(substring1);
        LocalDate date2 = formatterLocal(substring2);

        //set statusReservation
        StatusReservation statusReservation = StatusReservation.noset;

        LocalDate dateNow = LocalDate.now();
        if((dateNow.isBefore(date1)) && (dateNow.isBefore(date2))){
            statusReservation = StatusReservation.upcoming;
        }else if ((dateNow.isAfter(date1))&&(dateNow.isEqual(date2))){
            statusReservation = StatusReservation.active;

        }else if((dateNow.isAfter(date1))&&(dateNow.isAfter(date2))){
            statusReservation = StatusReservation.completed;

        }
        // Calc Days Stay
        long countOvernightStay = ChronoUnit.DAYS.between(date1, date2);
       //Get Actual Prices
        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));
        Price currentPrice = allPrices.get();
        Double price = 0.00;
        priceService.findAllPriceById();

        //Calculate price and totalPrice
        price += (int) countOvernightStay  * currentPrice.getPriceOvernightStay().doubleValue();

        if (reservationEditDTO.getFood().name().equals("YES")) {
            price += (int) countOvernightStay * currentPrice.getPriceFood().doubleValue();
        }
        if (reservationEditDTO.getTraining().name().equals("YES")) {
            price += currentPrice.getPriceTraining().doubleValue();
        }
        if (reservationEditDTO.getBathing().name().equals("YES")) {
            price += currentPrice.getPriceBathing().doubleValue();
        }
        if (reservationEditDTO.getCombing().name().equals("YES")) {
            price += currentPrice.getPriceCombing().doubleValue();
        }
        if (reservationEditDTO.getEars().name().equals("YES")) {
            price += currentPrice.getPriceEars().doubleValue();
        }
        if (reservationEditDTO.getPaws().name().equals("YES")) {
            price += currentPrice.getPricePaws().doubleValue();
        }
        if (reservationEditDTO.getNails().name().equals("YES")) {
            price += currentPrice.getPriceNails().doubleValue();
        }
        double totalPrice = 0.0;

        //calculate Discount
        Double discount = reservationEditDTO.getDiscount();
        if(discount!=null){
            totalPrice = price -(price*discount/100);
        }else {
            totalPrice = price;
            discount=0.0;
        }

        //Get field
        Long dogId = reservationEditDTO.getDog().getId();
        Long clientId = reservationEditDTO.getClient().getId();
        Long cellId = reservationEditDTO.getCell().getId();
        int countOvernightStay1 = (int) countOvernightStay;
        Food food = reservationEditDTO.getFood();
        Training training = reservationEditDTO.getTraining();
        Bathing bathing = reservationEditDTO.getBathing();
        Combing combing = reservationEditDTO.getCombing();
        Ears ears = reservationEditDTO.getEars();
        Paws paws = reservationEditDTO.getPaws();
        Nails nails = reservationEditDTO.getNails();
        BigDecimal bigDecimalPrice = BigDecimal.valueOf(price);
        BigDecimal bigDecimalTotalPrice = BigDecimal.valueOf(totalPrice);
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        // edit reservation
        reservationRepository.editReservation(
                clientId,
                dogId,
                date1,
                date2,
                countOvernightStay1,
                cellId,
                food,
                training,
                bathing,
                combing,
                ears,
                paws,
                nails,
                bigDecimalPrice,
                discount,
                bigDecimalTotalPrice,
                statusReservation,
                reservationId,
                editAuthorId);

        //set Cell Busy
        Cell cellCurrent = reservationEditDTO.getCell();
        Long id = cellCurrent.getId();
        if(statusReservation.name().equals("active")){
            cellService.setCellBusy(id);
        }
    }

    @Override
    public void setCellEmptyByReservationID(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        Reservation reservation = reservationOptional.get();
        Long cellId = reservation.getCell().getId();
        cellService.setCellEmpty(cellId);
    }

    @Override
    public void statusReservationsUpdateAndStatusCellsUpdateEverytimeTableReservationUpdateOrCall() {

        List<Reservation> allReservation = reservationRepository.findAllReservationById();
        for (Reservation reservation : allReservation) {
            //change reservation status
            Long reservationId = reservation.getId();
            LocalDate startDate = reservation.getStartDate();
            LocalDate endDate = reservation.getEndDate();
            LocalDate dateNow = LocalDate.now();

            StatusReservation statusReservation = StatusReservation.noset;
            if((dateNow.isBefore(startDate)) && (dateNow.isBefore(endDate))){
                statusReservation = StatusReservation.upcoming;
            }else if ((dateNow.isAfter(startDate))&&(dateNow.isBefore(endDate))||(dateNow.isEqual(endDate))){
                statusReservation = StatusReservation.active;

            }else if((dateNow.isAfter(startDate))&&(dateNow.isAfter(endDate))){
                statusReservation = StatusReservation.completed;

            }
            reservationRepository.updateStatusReservation(reservationId,statusReservation);
            //change Cell status

            Long cellId = reservation.getCell().getId();
            if(!statusReservation.name().equals("active")){

                cellService.setCellEmpty(cellId);

            }else {
                cellService.setCellBusy(cellId);
            }
        }


    }

    LocalDate formatterLocal(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return localDate;
    }
}
