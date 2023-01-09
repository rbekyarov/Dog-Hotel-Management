package rbekyarov.project.service;

import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.models.entity.Reservation;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public interface InvoiceService {
    List<Invoice> findAllInvoice();
    List<Invoice> findAllRealInvoice();
    List<Invoice> findAllCancelledInvoice();



    void addInvoice(Reservation reservation, HttpSession session);

    void cancellationInvoiceById(Long id);

    Optional<Invoice> findById(Long id);

}
