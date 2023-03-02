package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.service.InvoiceService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class InvoiceController extends BaseController {
   private final InvoiceService invoiceService;
   private final HttpSession session;

    public InvoiceController(InvoiceService invoiceService, HttpSession session) {
        this.invoiceService = invoiceService;
        this.session = session;
    }


    @GetMapping("/view/table/invoiceTable")
    public ModelAndView invoiceTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Invoice> invoices = invoiceService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = invoices.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        BigDecimal totalInvoicedPrice = invoiceService.getTotalInvoicedPrice();
        modelAndView.addObject("invoices", invoices);
        modelAndView.addObject("totalInvoicedPrice", totalInvoicedPrice);
        return super.view("/view/table/invoiceTable", "invoices", invoices,"pageNumbers", pageNumbers,"totalInvoicedPrice", totalInvoicedPrice);
    }
    @GetMapping("/view/table/invoiceCanceledTable")
    public ModelAndView invoiceCancelledTable(ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Invoice> invoices = invoiceService.findAllCancelledInvoice();
        modelAndView.addObject("invoices", invoices);
        return super.view("/view/table/invoiceCanceledTable", "invoices", invoices);
    }
    @GetMapping("view/table/invoice/view/{id}")
    public ModelAndView getInvoiceView(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Invoice invoice = invoiceService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        modelAndView.addObject("invoice", invoice);


        return super.view("/view/table/invoiceView", "invoice", invoice);

    }

    @GetMapping("view/table/invoice/remove/{id}")
    public String cancellationInvoice(@PathVariable Long id) {
        if(checkValidSession()) {
            return "redirect:view/login";
        }
        //delete invoice
        invoiceService.cancellationInvoiceById(id);

        return "redirect:/view/table/invoiceTable";
    }



    @RequestMapping(path = {"/","/view/table/searchInvoiceNumber"})
    public ModelAndView searchInvoiceNumber(ModelAndView modelAndView,@RequestParam("invoiceNumber") String invoiceNumber) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Invoice> invoices = new ArrayList<>();
        if(!invoiceNumber.equals("")) {
            invoices = invoiceService.listInvoiceById(Long.parseLong(invoiceNumber));
            modelAndView.addObject("invoices", invoices);
        }else {
            invoices = invoiceService.findAllRealInvoice();
            modelAndView.addObject("invoices", invoices);}
        return super.view("/view/table/invoiceTable", "invoices", invoices);
    }
    @RequestMapping(path = {"/","/view/table/searchInvoiceByClientEmail"})
    public ModelAndView searchClientEmail(ModelAndView modelAndView,@RequestParam("clientEmail") String clientEmail) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Invoice> invoices = new ArrayList<>();
        if(!clientEmail.equals("")) {
            invoices = invoiceService.listInvoiceByEmail(clientEmail);
            modelAndView.addObject("invoices", invoices);
        }else {
            invoices = invoiceService.findAllRealInvoice();
            modelAndView.addObject("invoices", invoices);}
        return super.view("/view/table/invoiceTable", "invoices", invoices);
    }

    public boolean checkValidSession(){
        Object user = session.getAttribute("user");
        Object admin = session.getAttribute("admin");

        if(admin ==null && user==null){
            return   true;

        }
        return false;
    }
}
