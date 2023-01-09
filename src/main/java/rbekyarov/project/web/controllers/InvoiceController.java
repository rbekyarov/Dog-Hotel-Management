package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.service.InvoiceService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class InvoiceController extends BaseController {
   private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/view/table/invoiceTable")
    public ModelAndView invoiceTable(ModelAndView modelAndView) {

        List<Invoice> invoices = invoiceService.findAllRealInvoice();
        modelAndView.addObject("invoices", invoices);
        return super.view("/view/table/invoiceTable", "invoices", invoices);
    }
    @GetMapping("/view/table/invoiceCanceledTable")
    public ModelAndView invoiceCancelledTable(ModelAndView modelAndView) {

        List<Invoice> invoices = invoiceService.findAllCancelledInvoice();
        modelAndView.addObject("invoices", invoices);
        return super.view("/view/table/invoiceCanceledTable", "invoices", invoices);
    }
    @GetMapping("view/table/invoice/view/{id}")
    public ModelAndView getInvoiceView(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {

        Invoice invoice = invoiceService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        modelAndView.addObject("invoice", invoice);


        return super.view("/view/table/invoiceView", "invoice", invoice);

    }

    @GetMapping("view/table/invoice/remove/{id}")
    public String cancellationInvoice(@PathVariable Long id) {

        //delete invoice
        invoiceService.cancellationInvoiceById(id);

        return "redirect:/view/table/invoiceTable";
    }

    @RequestMapping(path = {"/","/view/table/searchInvoiceNumber"})
    public ModelAndView searchInvoiceNumber(ModelAndView modelAndView,@RequestParam("invoiceNumber") String invoiceNumber) {
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
        List<Invoice> invoices = new ArrayList<>();
        if(!clientEmail.equals("")) {
            invoices = invoiceService.listInvoiceByEmail(clientEmail);
            modelAndView.addObject("invoices", invoices);
        }else {
            invoices = invoiceService.findAllRealInvoice();
            modelAndView.addObject("invoices", invoices);}
        return super.view("/view/table/invoiceTable", "invoices", invoices);
    }


}
