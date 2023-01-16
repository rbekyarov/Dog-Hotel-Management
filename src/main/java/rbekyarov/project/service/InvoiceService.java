package rbekyarov.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.models.entity.Reservation;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface InvoiceService {
    List<Invoice> findAllInvoice();
    List<Invoice> findAllRealInvoice();
    List<Invoice> findAllCancelledInvoice();



    void addInvoice(Reservation reservation, HttpSession session);

    void cancellationInvoiceById(Long id);

    Optional<Invoice> findById(Long id);

    List<Invoice> listInvoiceById(Long invoiceNumber);

    List<Invoice> listInvoiceByEmail(String clientEmail);
    Page<Invoice> findPaginated(Pageable pageable);

    BigDecimal getTotalInvoicedPrice();

    List<Invoice> findLastInvoices();
}
