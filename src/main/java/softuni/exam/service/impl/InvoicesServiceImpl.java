package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.entity.Invoice;
import softuni.exam.models.entity.Reservation;
import softuni.exam.repository.InvoiceRepository;
import softuni.exam.service.InvoiceService;
import softuni.exam.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class InvoicesServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;


    public InvoicesServiceImpl(InvoiceRepository invoiceRepository, UserService userService) {
        this.invoiceRepository = invoiceRepository;

        this.userService = userService;
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


        invoiceRepository.save(invoice);
    }

    @Override
    public void removeInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

}


