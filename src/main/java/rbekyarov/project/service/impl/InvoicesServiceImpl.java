package rbekyarov.project.service.impl;

import org.springframework.stereotype.Service;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.enums.Invoiced;
import rbekyarov.project.service.CompanyService;
import rbekyarov.project.repository.InvoiceRepository;
import rbekyarov.project.service.InvoiceService;
import rbekyarov.project.service.ReservationService;
import rbekyarov.project.service.UserService;

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


    public InvoicesServiceImpl(InvoiceRepository invoiceRepository, UserService userService, CompanyService companyService, ReservationService reservationService) {
        this.invoiceRepository = invoiceRepository;

        this.userService = userService;
        this.companyService = companyService;
        this.reservationService = reservationService;
    }

    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAllInvoice();
    }

    @Override
    public void addInvoice(Reservation reservation, HttpSession session) {

        Invoice invoice = new Invoice();
        //get Data from Reservation
        invoice.setClient(reservation.getClient());
        invoice.setDog(reservation.getDog());
        invoice.setDog(reservation.getDog());
        invoice.setStartDate(reservation.getStartDate());
        invoice.setEndDate(reservation.getEndDate());
        invoice.setCountStay(reservation.getCountOvernightStay());
        invoice.setCountStay(reservation.getCountOvernightStay());
        invoice.setCell(reservation.getCell());
        invoice.setFood(reservation.getFood());
        invoice.setTraining(reservation.getTraining());
        invoice.setBathing(reservation.getBathing());
        invoice.setCombing(reservation.getCombing());
        invoice.setEars(reservation.getEars());
        invoice.setPaws(reservation.getPaws());
        invoice.setNails(reservation.getNails());
        invoice.setPrice(reservation.getPrice());
        invoice.setDiscount(reservation.getDiscount());
        invoice.setTotalPrice(reservation.getTotalPrice());
        invoice.setCompany(reservation.getCompany());
        invoice.setReservationId(reservation.getId());

        // set dateCreated
        invoice.setDateCreate(LocalDate.now());

        //get and set Author
        invoice.setAuthor(userService.getAuthorFromSession(session));
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


