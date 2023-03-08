package rbekyarov.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpSession;
import rbekyarov.project.models.entity.*;
import rbekyarov.project.models.entity.enums.Invoiced;
import rbekyarov.project.repository.InvoiceRepository;
import rbekyarov.project.service.impl.InvoicesServiceImpl;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InvoicesServiceImplTest {

    private InvoiceRepository invoiceRepository;
    private UserService userService;
    private CompanyService companyService;
    private ReservationService reservationService;
    private PriceService priceService;

    private InvoicesServiceImpl invoicesServiceImpl;

    @BeforeEach
    public void setUp() {
        invoiceRepository = mock(InvoiceRepository.class);
        userService = mock(UserService.class);
        companyService = mock(CompanyService.class);
        reservationService = mock(ReservationService.class);
        priceService = mock(PriceService.class);

        invoicesServiceImpl = new InvoicesServiceImpl(invoiceRepository, userService, companyService, reservationService,
                priceService);
    }

    @Test
    public void testFindAllInvoice() {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(new Invoice());
        when(invoiceRepository.findAllInvoice()).thenReturn(invoiceList);

        List<Invoice> returnedInvoiceList = invoicesServiceImpl.findAllInvoice();

        assertEquals(1, returnedInvoiceList.size());
        verify(invoiceRepository, times(1)).findAllInvoice();
    }

    @Test
    public void testFindAllRealInvoice() {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(new Invoice());
        when(invoiceRepository.findAllRealInvoice()).thenReturn(invoiceList);

        List<Invoice> returnedInvoiceList = invoicesServiceImpl.findAllRealInvoice();

        assertEquals(1, returnedInvoiceList.size());
        verify(invoiceRepository, times(1)).findAllRealInvoice();
    }

    @Test
    public void testFindAllCancelledInvoice() {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(new Invoice());
        when(invoiceRepository.findAllCancelledInvoice()).thenReturn(invoiceList);

        List<Invoice> returnedInvoiceList = invoicesServiceImpl.findAllCancelledInvoice();

        assertEquals(1, returnedInvoiceList.size());
        verify(invoiceRepository, times(1)).findAllCancelledInvoice();
    }
    @Test
    public void testCancellationInvoiceById() {
        // given
        Long id = 1L;
        BigDecimal currentBalance = BigDecimal.valueOf(1000);
        BigDecimal totalPrice = BigDecimal.valueOf(100);
        Invoice invoice = new Invoice();
        invoice.setId(id);
        invoice.setTotalPrice(totalPrice);
        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));
        when(companyService.getCurrentBalance()).thenReturn(currentBalance);

        // when
        invoicesServiceImpl.cancellationInvoiceById(id);

        // then
        verify(companyService).editBalance(currentBalance.subtract(totalPrice));
        verify(reservationService).changeInvoicedStatus(invoice.getReservationId(), Invoiced.NO);
        verify(invoiceRepository).setCanceledOnInvoiced(invoice.getId());
        assertEquals(LocalDate.now(), invoice.getCancelledDateInvoice());
    }
    @Test
    public void testFindById() {
        // given
        Long id = 1L;
        Invoice invoice = new Invoice();
        invoice.setId(id);
        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));

        // when
        Optional<Invoice> result = invoicesServiceImpl.findById(id);

        // then
        assertTrue(result.isPresent());
        assertEquals(invoice, result.get());
    }
    @Test
    public void testListInvoiceById() {
        // given
        Long invoiceNumber = 1L;
        Invoice invoice = new Invoice();
        invoice.setId(invoiceNumber);
        List<Invoice> expected = Collections.singletonList(invoice);
        when(invoiceRepository.listInvoiceById(invoiceNumber)).thenReturn(expected);

        // when
        List<Invoice> result = invoicesServiceImpl.listInvoiceById(invoiceNumber);

        // then
        assertEquals(expected, result);
    }
    @Test
    public void testListInvoiceByEmail() {
        // given
        String clientEmail = "test@test.com";
        Invoice invoice = new Invoice();
        invoice.setClientEmail(clientEmail);
        List<Invoice> expected = Collections.singletonList(invoice);
        when(invoiceRepository.listInvoiceByEmail(clientEmail)).thenReturn(expected);

        // when
        List<Invoice> result = invoicesServiceImpl.listInvoiceByEmail(clientEmail);

        // then
        assertEquals(expected, result);
    }
    @Test
    public void testGetTotalInvoicedPrice() {
        // Setup
        List<Invoice> invoices = Arrays.asList(
                new Invoice(new BigDecimal("10")),
                new Invoice(new BigDecimal("20")),
                new Invoice(new BigDecimal("30"))
        );
        Mockito.when(invoiceRepository.findAllRealInvoice()).thenReturn(invoices);

        // Execution
        BigDecimal result = invoicesServiceImpl.getTotalInvoicedPrice();

        // Assertion
        assertEquals(new BigDecimal("60"), result);
    }
    @Test
    public void testFindLastInvoices() {
        // Setup
        List<Invoice> invoices = Arrays.asList(
                new Invoice(),
                new Invoice(),
                new Invoice()
        );
        Mockito.when(invoiceRepository.findLastInvoices()).thenReturn(invoices);

        // Execution
        List<Invoice> result = invoicesServiceImpl.findLastInvoices();

        // Assertion
        assertEquals(invoices, result);
    }
    @Test
    public void testGetTop3Clients() {
        // Setup
        List<String> clients = Arrays.asList("Client 1", "Client 2", "Client 3");
        Mockito.when(invoiceRepository.getTop3Client()).thenReturn(clients);

        // Execution
        List<String> result = invoicesServiceImpl.getTop3Clients();

        // Assertion
        assertEquals(clients, result);
    }
    @Test
    public void testGetAllInvoiceForRest() {
        // Setup
        List<Invoice> invoices = Arrays.asList(
                new Invoice(),
                new Invoice(),
                new Invoice()
        );
        Mockito.when(invoiceRepository.findAllInvoice()).thenReturn(invoices);

        // Execution
        List<Invoice> result = invoicesServiceImpl.getAllInvoiceForRest();

        // Assertion
        assertEquals(invoices, result);
    }
    @Test
    public void testFindPaginated() {
        // Setup
        int pageSize = 2;
        int currentPage = 0;
        List<Invoice> invoices = Arrays.asList(
                new Invoice(),
                new Invoice(),
                new Invoice()
        );
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Mockito.when(invoiceRepository.findAllRealInvoice()).thenReturn(invoices);

        // Execution
        Page<Invoice> result = invoicesServiceImpl.findPaginated(pageable);

        // Assertion
        assertEquals(pageSize, result.getSize());
        assertEquals(currentPage, result.getNumber());
        assertEquals(invoices.size(), result.getTotalElements());
    }

}
