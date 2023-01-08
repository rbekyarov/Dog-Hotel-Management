package rbekyarov.project.service.impl;

import org.springframework.stereotype.Service;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Invoiced;
import rbekyarov.project.service.*;
import rbekyarov.project.repository.InvoiceRepository;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class InvoicesServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;
    private final CompanyService companyService;
    private final ReservationService reservationService;
    private final PriceService priceService;


    public InvoicesServiceImpl(InvoiceRepository invoiceRepository, UserService userService, CompanyService companyService, ReservationService reservationService, PriceService priceService) {
        this.invoiceRepository = invoiceRepository;

        this.userService = userService;
        this.companyService = companyService;
        this.reservationService = reservationService;
        this.priceService = priceService;
    }

    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAllInvoice();
    }

    @Override
    public void addInvoice(Reservation reservation, HttpSession session) {

        Invoice invoice = new Invoice();
        //get Data from Reservation
           //Company
        invoice.setCompanyName(reservation.getCompany().getName());
        invoice.setCompanyCityName(reservation.getCompany().getCity().getName());
        invoice.setCompanyAddress(reservation.getCompany().getAddress());
        invoice.setCompanyVatNumber(reservation.getCompany().getVatNumber());
        invoice.setCompanyEmail(reservation.getCompany().getEmail());
        invoice.setCompanyBankName(reservation.getCompany().getBankName());
        invoice.setCompanyBankAccount(reservation.getCompany().getBankAccount());
        invoice.setCompanyBankAccount(reservation.getCompany().getBankAccount());
        invoice.setCompanyManagerName(reservation.getCompany().getManagerName());

        invoice.setDogName(reservation.getDog().getName());
        invoice.setCellCode(reservation.getCell().getCode());
        invoice.setReservationId(reservation.getId());
        invoice.setCountStay(reservation.getCountOvernightStay());

           //Enum
        invoice.setFood(reservation.getFood());
        invoice.setTraining(reservation.getTraining());
        invoice.setBathing(reservation.getBathing());
        invoice.setCombing(reservation.getCombing());
        invoice.setEars(reservation.getEars());
        invoice.setPaws(reservation.getPaws());
        invoice.setNails(reservation.getNails());
           // PRICES
        invoice.setPrice(reservation.getPrice());
        invoice.setDiscount(reservation.getDiscount());
        invoice.setTotalPrice(reservation.getTotalPrice());

           //CURRENT PRICES
        invoice.setCountStayPrice(priceService.getOvernightStayCurrentPrice());
        invoice.setFoodPrice(priceService.getFoodCurrentPrice());
        invoice.setTrainingPrice(priceService.getTrainingCurrentPrice());
        invoice.setBathingPrice(priceService.getBathingCurrentPrice());
        invoice.setCombingPrice(priceService.getCombingCurrentPrice());
        invoice.setEarsPrice(priceService.getEarsCurrentPrice());
        invoice.setPawsPrice(priceService.getPawsCurrentPrice());
        invoice.setNailsPrice(priceService.getNailsCurrentPrice());

        //CLIENT
        invoice.setClientAddress(reservation.getClient().getAddress());
        invoice.setClientCityName(reservation.getClient().getCity().getName());
        invoice.setClientEmail(reservation.getClient().getEmail());
        invoice.setClientPhone(reservation.getClient().getPhone());
        invoice.setClientName(reservation.getClient().getFirstName()+" "+reservation.getClient().getLastName());


        // set dateCreated
        invoice.setDateCreate(LocalDate.now());

        //get and set Author
        User authorFromSession = userService.getAuthorFromSession(session);
        invoice.setAuthorName(userService.getAuthorFromSession(session).getUsername());
        //create invoice
        invoiceRepository.save(invoice);
        //change Company Balance
        BigDecimal currentBalance = companyService.getCurrentBalance();
        BigDecimal totalPrice = invoice.getTotalPrice();
        companyService.editBalance(currentBalance.add(totalPrice));
        //change Reservation Invoiced Status on "YES"
        reservationService.changeInvoicedStatus(reservation.getId(), Invoiced.YES);

    }

    @Override
    public void removeInvoiceById(Long id) {

        //change Company Balance
        Optional<Invoice> invoiceById = invoiceRepository.findById(id);
        Invoice invoice = invoiceById.get();
        BigDecimal currentBalance = companyService.getCurrentBalance();
        BigDecimal totalPrice = invoice.getTotalPrice();
        companyService.editBalance(currentBalance.subtract(totalPrice));
        //change Reservation Invoiced Status on "NO"
        reservationService.changeInvoicedStatus(invoice.getReservationId(), Invoiced.NO);
        //delete Invoice
        invoiceRepository.deleteById(id);
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

}


