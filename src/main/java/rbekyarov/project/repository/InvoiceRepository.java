package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Invoice;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("select i from Invoice as i order by i.id asc ")
    List<Invoice> findAllInvoice();
    @Query("select i from Invoice as i where i.cancellationInvoice='NO' order by i.id asc ")
    List<Invoice> findAllRealInvoice();
    @Query("select i from Invoice as i where i.cancellationInvoice='YES' order by i.id asc ")
    List<Invoice> findAllCancelledInvoice();

    Optional<Invoice> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Invoice as i SET i.cancellationInvoice='YES' where i.id=:id ")
    void setCanceledOnInvoiced( @Param("id") Long id);
    @Query("select i from Invoice as i where i.id=:invoiceNumber")
    List<Invoice> listInvoiceById(@Param("invoiceNumber") Long invoiceNumber);
    @Query("select i from Invoice as i where i.clientEmail=:clientEmail")
    List<Invoice> listInvoiceByEmail(@Param("clientEmail")String clientEmail);
}
