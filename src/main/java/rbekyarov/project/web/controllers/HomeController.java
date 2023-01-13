package rbekyarov.project.web.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rbekyarov.project.models.entity.*;
import rbekyarov.project.service.UserService;
import rbekyarov.project.service.*;

import javax.servlet.http.HttpSession;
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
        List<Reservation>reservations = reservationService.findAllReservationByDesc();
        List<Cell>cells = cellService.findAllCellById();
        List<Cost>  costs = costService.findAllCostByDesc();
        List<Invoice>  invoices = invoiceService.findAllInvoice();
        Optional<Company> companyOptional = companyService.findById(1L);
        Company company = companyOptional.get();
        return super.view(viewName);
    }
}
