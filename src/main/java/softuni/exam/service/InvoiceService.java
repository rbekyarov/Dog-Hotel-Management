package softuni.exam.service;

import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.dto.ReservationEditDTO;
import softuni.exam.models.entity.Invoice;
import softuni.exam.models.entity.Reservation;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public interface InvoiceService {
    List<Invoice> findAllInvoice();



    void addInvoice(Reservation reservation, HttpSession session);

    void removeInvoiceById(Long id);

    Optional<Invoice> findById(Long id);

}
