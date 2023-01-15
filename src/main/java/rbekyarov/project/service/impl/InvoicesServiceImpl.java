package rbekyarov.project.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.enums.CancellationInvoice;
import rbekyarov.project.models.entity.enums.Invoiced;
import rbekyarov.project.service.*;
import rbekyarov.project.repository.InvoiceRepository;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
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
    public List<Invoice> findAllRealInvoice() {
        return invoiceRepository.findAllRealInvoice();
    }

    @Override
    public List<Invoice> findAllCancelledInvoice() {
        return invoiceRepository.findAllCancelledInvoice();
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
        invoice.setDeworming(reservation.getDeworming());
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
        Cell cell = reservation.getCell();
        if(cell.getCode().contains("S")){
            invoice.setCountStayPrice(priceService.getCurrentPriceStayForCellS());
        }else if(cell.getCode().contains("M")){
            invoice.setCountStayPrice(priceService.getCurrentPriceStayForCellM());
        }else if(cell.getCode().contains("L")){
            invoice.setCountStayPrice(priceService.getCurrentPriceStayForCellL());
        }
        invoice.setFoodPrice(priceService.getFoodCurrentPrice());
        invoice.setDewormingPrice(priceService.getDewormingCurrentPrice());
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
        //set default canceledStatus "NO"
        invoice.setCancellationInvoice(CancellationInvoice.NO);

        // set dateCreated
        invoice.setDateCreate(LocalDate.now());

        //get and set Author
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
    public void cancellationInvoiceById(Long id) {

        //change Company Balance
        Optional<Invoice> invoiceById = invoiceRepository.findById(id);
        Invoice invoice = invoiceById.get();
        BigDecimal currentBalance = companyService.getCurrentBalance();
        BigDecimal totalPrice = invoice.getTotalPrice();
        companyService.editBalance(currentBalance.subtract(totalPrice));
        //change Reservation Invoiced Status on "NO"
        reservationService.changeInvoicedStatus(invoice.getReservationId(), Invoiced.NO);
        invoice.setCancelledDateInvoice(LocalDate.now());
        //set Invoice - cancellation
        invoiceRepository.setCanceledOnInvoiced(invoice.getId());

    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public List<Invoice> listInvoiceById(Long invoiceNumber) {
        return invoiceRepository.listInvoiceById(invoiceNumber);
    }

    @Override
    public List<Invoice> listInvoiceByEmail(String clientEmail) {
        return invoiceRepository.listInvoiceByEmail(clientEmail);
    }

    @Override
    public Page<Invoice> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Invoice> list;
        List<Invoice> invoices = invoiceRepository.findAllRealInvoice();
        if (invoices.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, invoices.size());
            list = invoices.subList(startItem, toIndex);
        }

        Page<Invoice> invoicesPage = new PageImpl<Invoice>(list, PageRequest.of(currentPage, pageSize), invoices.size());

        return invoicesPage;
    }

}


