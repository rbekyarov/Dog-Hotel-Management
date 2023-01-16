package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.ReservationDTO;
import rbekyarov.project.models.dto.ReservationEditDTO;
import rbekyarov.project.models.entity.*;
import rbekyarov.project.models.entity.enums.*;
import rbekyarov.project.repository.DogRepository;
import rbekyarov.project.repository.ReservationRepository;
import rbekyarov.project.service.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final PriceService priceService;
    private final ModelMapper modelMapper;
    private final CellService cellService;
    private final UserService userService;
    private final CompanyService companyService;
    private final DogRepository dogRepository;
    private final DogService dogService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, PriceService priceService, ModelMapper modelMapper, CellService cellService, UserService userService, CompanyService companyService,
                                  DogRepository dogRepository, DogService dogService) {
        this.reservationRepository = reservationRepository;
        this.priceService = priceService;
        this.modelMapper = modelMapper;
        this.cellService = cellService;
        this.userService = userService;
        this.companyService = companyService;
        this.dogRepository = dogRepository;
        this.dogService = dogService;
    }

    @Override
    public List<Reservation> findAllReservationByDesc() {
        return reservationRepository.findAllReservationByDesc();
    }

    @Override
    public void addReservation(ReservationDTO reservationDTO, HttpSession session) {
        // count date calculate

        String startDateDTO = reservationDTO.getStartDate();
        String endDateDTO = reservationDTO.getEndDate();
        String substring1 = startDateDTO.substring(0, 10);
        String substring2 = endDateDTO.substring(0, 10);
        LocalDate startDate = formatterLocal(substring1);
        LocalDate endDate = formatterLocal(substring2);

        //set statusReservation
        StatusReservation statusReservation = StatusReservation.unknown;

        LocalDate dateNow = LocalDate.now();
        if ((dateNow.isBefore(startDate)) && (dateNow.isBefore(endDate))) {
            statusReservation = StatusReservation.upcoming;
        } else if ((dateNow.isEqual(startDate) || (dateNow.isAfter(startDate))) && ((dateNow.isBefore(endDate)) || (dateNow.isEqual(endDate)))) {
            statusReservation = StatusReservation.active;

        } else if ((dateNow.isAfter(startDate)) && (dateNow.isAfter(endDate))) {
            statusReservation = StatusReservation.completed;

        }


        long countOvernightStay = ChronoUnit.DAYS.between(startDate, endDate);
        if (countOvernightStay<0){
            countOvernightStay=0;
        }


        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));
        Price currentPrice = allPrices.get();

        Double price = 0.00;
        priceService.findAllPriceById();
        //Get dog weight
        Long dogID = reservationDTO.getDog().getId();
        Integer weight = dogService.getWeightById(dogID);
        double currentPriceStay = 0.0;
        if (weight <= 10) {
            currentPriceStay = currentPrice.getPriceStayS().doubleValue();
        } else if (weight <= 20) {
            currentPriceStay = currentPrice.getPriceStayM().doubleValue();
        }else {
            currentPriceStay = currentPrice.getPriceStayL().doubleValue();
        }


        price += (int) countOvernightStay * currentPriceStay;

        if (reservationDTO.getFood().name().equals("YES")) {
            price += (int) countOvernightStay * currentPrice.getPriceFood().doubleValue();
        }
        if (reservationDTO.getDeworming().name().equals("YES")) {
            //change date Dog Deworming
            dogRepository.editDogDateDewormingById(reservationDTO.getDog().getId(),LocalDate.now());
            //setPrice
            price += currentPrice.getPriceDeworming().doubleValue();
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
        if (discount != null) {
            //discount can not be over 20%
            if (discount > 20.00) {
                discount = 20.00;
            }
            totalPrice = price - (price * discount / 100);
        } else {
            totalPrice = price;
            discount = 0.0;
        }

        Cell cellCurrent = reservationDTO.getCell();
        reservation.setCell(cellCurrent);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setDiscount(discount);
        reservation.setClient(reservationDTO.getClient());
        reservation.setDog(reservationDTO.getDog());
        reservation.setCountOvernightStay((int) countOvernightStay);
        reservation.setTotalPrice(new BigDecimal(totalPrice));
        reservation.setPrice(new BigDecimal(price));
        reservation.setStatusReservation(statusReservation);
        reservation.setCompany(companyService.findById(1l).get());
        //set Default InvoicedStatus
        reservation.setInvoiced(Invoiced.NO);
        // set dateCreated
        reservation.setDateCreate(LocalDate.now());

        Long id = cellCurrent.getId();
        if (statusReservation.name().equals("active")) {
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
        String startDateDto = reservationEditDTO.getStartDate();
        String endDateDto = reservationEditDTO.getEndDate();
        String substring1 = startDateDto.substring(0, 10);
        String substring2 = endDateDto.substring(0, 10);
        LocalDate startDate = formatterLocal(substring1);
        LocalDate endDate = formatterLocal(substring2);

        //set statusReservation
        StatusReservation statusReservation = StatusReservation.unknown;

        LocalDate dateNow = LocalDate.now();
        if ((dateNow.isBefore(startDate)) && (dateNow.isBefore(endDate))) {
            statusReservation = StatusReservation.upcoming;
        } else if ((dateNow.isEqual(startDate) || (dateNow.isAfter(startDate))) && ((dateNow.isBefore(endDate)) || (dateNow.isEqual(endDate)))) {
            statusReservation = StatusReservation.active;

        } else if ((dateNow.isAfter(startDate)) && (dateNow.isAfter(endDate))) {
            statusReservation = StatusReservation.completed;

        }
        // Calc Days Stay
        long countOvernightStay = ChronoUnit.DAYS.between(startDate, endDate);
        if (countOvernightStay<0){
            countOvernightStay=0;
        }
        //Get Actual Prices
        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));
        Price currentPrice = allPrices.get();
        Double price = 0.00;
        priceService.findAllPriceById();

        //Get dog weight
        Long dogID = reservationEditDTO.getDog().getId();
        Integer weight = dogService.getWeightById(dogID);
        double currentPriceStay = 0.0;
        if (weight <= 10) {
            currentPriceStay = currentPrice.getPriceStayS().doubleValue();
        } else if (weight <= 20) {
            currentPriceStay = currentPrice.getPriceStayM().doubleValue();
        }else {
            currentPriceStay = currentPrice.getPriceStayL().doubleValue();
        }
        //Calculate price and totalPrice
        price += (int) countOvernightStay * currentPriceStay;

        if (reservationEditDTO.getFood().name().equals("YES")) {
            price += (int) countOvernightStay * currentPrice.getPriceFood().doubleValue();
        }
        if (reservationEditDTO.getDeworming().name().equals("YES")) {
            //change date Dog Deworming
            dogRepository.editDogDateDewormingById(reservationEditDTO.getDog().getId(),LocalDate.now());
            //setPrice
            price += currentPrice.getPriceDeworming().doubleValue();
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
        if (discount != null) {
            totalPrice = price - (price * discount / 100);
        } else {
            totalPrice = price;
            discount = 0.0;
        }

        //Get field
        Long dogId = reservationEditDTO.getDog().getId();
        Long clientId = reservationEditDTO.getClient().getId();
        Long cellId = reservationEditDTO.getCell().getId();
        int countOvernightStay1 = (int) countOvernightStay;
        Food food = reservationEditDTO.getFood();
        Deworming deworming = reservationEditDTO.getDeworming();
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

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        // edit reservation
        reservationRepository.editReservation(
                clientId,
                dogId,
                startDate,
                endDate,
                countOvernightStay1,
                cellId,
                food,
                deworming,
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
                editAuthorId,
                dateEdit);

        //set Cell Busy
        Cell cellCurrent = reservationEditDTO.getCell();
        Long id = cellCurrent.getId();
        if (statusReservation.name().equals("active")) {
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

        List<Reservation> allReservation = reservationRepository.findAllReservationByDesc();
        for (Reservation reservation : allReservation) {
            //change reservation status
            Long reservationId = reservation.getId();
            LocalDate startDate = reservation.getStartDate();
            LocalDate endDate = reservation.getEndDate();
            LocalDate dateNow = LocalDate.now();

            StatusReservation statusReservation = StatusReservation.unknown;
            if ((dateNow.isBefore(startDate)) && (dateNow.isBefore(endDate))) {
                statusReservation = StatusReservation.upcoming;
            } else if ((dateNow.isEqual(startDate) || (dateNow.isAfter(startDate))) && ((dateNow.isBefore(endDate)) || (dateNow.isEqual(endDate)))) {
                statusReservation = StatusReservation.active;

            } else if ((dateNow.isAfter(startDate)) && (dateNow.isAfter(endDate))) {
                statusReservation = StatusReservation.completed;

            }
            reservationRepository.updateStatusReservation(reservationId, statusReservation);
            //change Cell status

            Long cellId = reservation.getCell().getId();
            if (!statusReservation.name().equals("active")) {

                cellService.setCellEmpty(cellId);

            } else {
                cellService.setCellBusy(cellId);
            }
        }


    }

    @Override
    public void changeInvoicedStatus(Long id, Invoiced invoiced) {
        reservationRepository.changeInvoiced(id, invoiced);
    }

    @Override
    public List<Reservation> listReservationByClientEmail(String clientEmail) {
        return reservationRepository.listReservationByClientEmail(clientEmail);
    }

    @Override
    public Page<Reservation> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Reservation> list;
        List<Reservation> reservations = reservationRepository.findAllReservationByDesc();
        if (reservations.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, reservations.size());
            list = reservations.subList(startItem, toIndex);
        }

        Page<Reservation> reservationPage = new PageImpl<Reservation>(list, PageRequest.of(currentPage, pageSize), reservations.size());

        return reservationPage;
    }

    @Override
    public List<Reservation> listReservationById(long reservationNumber) {
        return reservationRepository.listReservationById(reservationNumber);
    }

    @Override
    public List<Reservation> findAllActiveReservation() {
        return reservationRepository.findAllActiveReservation();
    }

    @Override
    public List<Dog> findActiveReservedDogs() {
        return reservationRepository.findActiveReservedDogs();
    }

    LocalDate formatterLocal(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return localDate;
    }
}
