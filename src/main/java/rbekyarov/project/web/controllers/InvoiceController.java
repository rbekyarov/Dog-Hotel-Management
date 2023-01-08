package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.service.InvoiceService;
import java.util.List;


@Controller
public class InvoiceController extends BaseController {
   private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/view/table/invoiceTable")
    public ModelAndView invoiceTable(ModelAndView modelAndView) {

        List<Invoice> invoices = invoiceService.findAllInvoice();
        modelAndView.addObject("invoices", invoices);
        return super.view("/view/table/invoiceTable", "invoices", invoices);
    }
    @GetMapping("view/table/invoice/view/{id}")
    public ModelAndView getInvoiceView(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {

        Invoice invoice = invoiceService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        modelAndView.addObject("invoice", invoice);


        return super.view("/view/table/invoiceView", "invoice", invoice);

    }

    @GetMapping("view/table/invoice/remove/{id}")
    public String removeInvoice(@PathVariable Long id) {

        //delete invoice
        invoiceService.removeInvoiceById(id);

        return "redirect:/view/table/invoiceTable";
    }




}
