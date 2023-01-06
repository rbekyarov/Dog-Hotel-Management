package softuni.exam.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.entity.*;
import softuni.exam.service.*;
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


    @GetMapping("view/table/invoice/remove/{id}")
    public String removeInvoice(@PathVariable Long id) {

        //delete invoice
        invoiceService.removeInvoiceById(id);

        return "redirect:/view/table/invoiceTable";
    }




}
