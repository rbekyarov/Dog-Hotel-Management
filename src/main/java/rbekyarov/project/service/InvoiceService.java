package rbekyarov.project.service;

import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.models.entity.Reservation;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public interface InvoiceService {
    List<Invoice> findAllInvoice();



    void addInvoice(Reservation reservation, HttpSession session);

    void removeInvoiceById(Long id);

    Optional<Invoice> findById(Long id);

}
