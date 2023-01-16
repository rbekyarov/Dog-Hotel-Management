package rbekyarov.project.web.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rbekyarov.project.models.entity.*;
import rbekyarov.project.service.UserService;
import rbekyarov.project.service.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController extends BaseController {

    private final UserService userService;
    private final ReservationService reservationService;
    private final CellService cellService;
    private final CompanyService companyService;
    private final CostService costService;
    private final InvoiceService invoiceService;

    public HomeController(UserService userService, ReservationService reservationService, CellService cellService, CompanyService companyService, CostService costService, InvoiceService invoiceService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.cellService = cellService;
        this.companyService = companyService;
        this.costService = costService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session) {
        Object user = session.getAttribute("user");
        Object admin = session.getAttribute("admin");

        if((admin !=null) ||(user!=null)){
            modelAndView.setViewName("/view/home");
        }else {
            modelAndView.setViewName("/index");
        }
        String viewName = modelAndView.getViewName();

        return super.view(viewName);
    }

    @GetMapping("/view/home")
    public ModelAndView home(ModelAndView modelAndView) {

        List<Reservation>activeReservations = reservationService.findAllActiveReservationLimit3();
        List<Reservation>upcomingReservations = reservationService.findAllUpcomingReservations();


        List<Cell>cells = cellService.findAllCellById();
        List<Cell>cellsEmpty = cellService.findAllEmptyCells();
        List<Cost>  costs = costService.findLast2Cost();
        List<Invoice>  invoices = invoiceService.findLastInvoices();
        BigDecimal totalInvoicedPrice = invoiceService.getTotalInvoicedPrice();
        BigDecimal totalAmountCost = costService.getTotalAmountCost();
        Optional<Company> companyOptional = companyService.findById(1L);
        Company company = companyOptional.get();
        modelAndView.addObject("activeReservations",activeReservations);
        modelAndView.addObject("upcomingReservations",upcomingReservations);
        modelAndView.addObject("cellsEmpty",cellsEmpty);
        modelAndView.addObject("costs",costs);
        modelAndView.addObject("invoices",invoices);
        modelAndView.addObject("company",company);
        modelAndView.addObject("totalInvoicedPrice",totalInvoicedPrice);
        modelAndView.addObject("totalAmountCost",totalAmountCost);

        return super.view("/view/home","company",company,"invoices",invoices,"costs",costs,"cellsEmpty",cellsEmpty,"activeReservations",activeReservations,"upcomingReservations",upcomingReservations,"totalInvoicedPrice",totalInvoicedPrice,"totalAmountCost",totalAmountCost);
    }
}
