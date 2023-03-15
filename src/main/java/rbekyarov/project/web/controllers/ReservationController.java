package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rbekyarov.project.models.dto.ReservationDTO;
import rbekyarov.project.models.dto.ReservationEditDTO;
import rbekyarov.project.models.entity.*;
import rbekyarov.project.repository.ClientRepository;
import rbekyarov.project.service.*;
import rbekyarov.project.repository.CellRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ReservationController extends BaseController {
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final CellService cellService;
    private final DogService dogService;
    private final PriceService priceService;
    private final InvoiceService invoiceService;
    private final CellRepository cellRepository;
    private final ClientRepository clientRepository;
    private final HttpSession session;


    public ReservationController(ReservationService reservationService, ClientService clientService, CellService cellService, DogService dogService, PriceService priceService, InvoiceService invoiceService, CellRepository cellRepository,
                                 ClientRepository clientRepository, HttpSession session) {
        this.reservationService = reservationService;
        this.clientService = clientService;
        this.cellService = cellService;
        this.dogService = dogService;
        this.priceService = priceService;
        this.invoiceService = invoiceService;
        this.cellRepository = cellRepository;
        this.clientRepository = clientRepository;
        this.session = session;
    }

    @GetMapping("/view/table/reservationTable")
    public ModelAndView reservationTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Reservation> reservations = reservationService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        int totalPages = reservations.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        String url = "reservationTable";
        modelAndView.addObject("reservations", reservations);
        List<Invoice> allRealInvoice = invoiceService.findAllRealInvoice();
        modelAndView.addObject("allRealInvoice", allRealInvoice);
        int countActiveReservation = reservationService.getCountActiveReservation();
        modelAndView.addObject("countActiveReservation", countActiveReservation);
        int countUpcomingReservation = reservationService.getCountUpcomingReservation();
        modelAndView.addObject("countUpcomingReservation", countUpcomingReservation);
        int countCompletedReservation = reservationService.getCountCompletedReservation();
        modelAndView.addObject("countCompletedReservation", countCompletedReservation);
        modelAndView.addObject("url", url);
        reservationService.statusReservationsUpdate();
        reservationService.statusCellsUpdateEmpty();

        return super.view("/view/table/reservationTable",
                "reservations", reservations,
                "pageNumbers", pageNumbers,
                "allRealInvoice", allRealInvoice,
                "countActiveReservation", countActiveReservation,
                "countUpcomingReservation", countUpcomingReservation,
                "countCompletedReservation", countCompletedReservation,
                "url", url);
    }
    @GetMapping("/view/table/reservationTableUpcoming")
    public ModelAndView reservationTableUpcoming(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Reservation> reservations = reservationService.findPaginatedUpcoming(PageRequest.of(currentPage - 1, pageSize));
        int totalPages = reservations.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        String url = "reservationTableUpcoming";
        modelAndView.addObject("reservations", reservations);
        List<Invoice> allRealInvoice = invoiceService.findAllRealInvoice();
        modelAndView.addObject("allRealInvoice", allRealInvoice);
        int countActiveReservation = reservationService.getCountActiveReservation();
        modelAndView.addObject("countActiveReservation", countActiveReservation);
        int countUpcomingReservation = reservationService.getCountUpcomingReservation();
        modelAndView.addObject("countUpcomingReservation", countUpcomingReservation);
        int countCompletedReservation = reservationService.getCountCompletedReservation();
        modelAndView.addObject("countCompletedReservation", countCompletedReservation);
        modelAndView.addObject("url", url);
        reservationService.statusReservationsUpdate();
        reservationService.statusCellsUpdateEmpty();
        return super.view("/view/table/reservationTable",
                "reservations", reservations,
                "pageNumbers", pageNumbers,
                "allRealInvoice", allRealInvoice,
                "countActiveReservation", countActiveReservation,
                "countUpcomingReservation", countUpcomingReservation,
                "countCompletedReservation", countCompletedReservation,
                "url", url);
    }
    @GetMapping("/view/table/reservationTableActive")
    public ModelAndView reservationTableActive(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Reservation> reservations = reservationService.findPaginatedActive(PageRequest.of(currentPage - 1, pageSize));
        int totalPages = reservations.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        String url = "reservationTableActive";
        modelAndView.addObject("reservations", reservations);
        List<Invoice> allRealInvoice = invoiceService.findAllRealInvoice();
        modelAndView.addObject("allRealInvoice", allRealInvoice);
        int countActiveReservation = reservationService.getCountActiveReservation();
        modelAndView.addObject("countActiveReservation", countActiveReservation);
        int countUpcomingReservation = reservationService.getCountUpcomingReservation();
        modelAndView.addObject("countUpcomingReservation", countUpcomingReservation);
        int countCompletedReservation = reservationService.getCountCompletedReservation();
        modelAndView.addObject("countCompletedReservation", countCompletedReservation);
        modelAndView.addObject("url", url);
        reservationService.statusReservationsUpdate();
        reservationService.statusCellsUpdateEmpty();
        return super.view("/view/table/reservationTable",
                "reservations", reservations,
                "pageNumbers", pageNumbers,
                "allRealInvoice", allRealInvoice,
                "countActiveReservation", countActiveReservation,
                "countUpcomingReservation", countUpcomingReservation,
                "countCompletedReservation", countCompletedReservation,
                "url", url);
    }
    @GetMapping("/view/table/reservationTableCompleted")
    public ModelAndView reservationTableCompleted(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Reservation> reservations = reservationService.findPaginatedCompleted(PageRequest.of(currentPage - 1, pageSize));
        int totalPages = reservations.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        String url = "reservationTableCompleted";
        modelAndView.addObject("reservations", reservations);
        List<Invoice> allRealInvoice = invoiceService.findAllRealInvoice();
        modelAndView.addObject("allRealInvoice", allRealInvoice);
        int countActiveReservation = reservationService.getCountActiveReservation();
        modelAndView.addObject("countActiveReservation", countActiveReservation);
        int countUpcomingReservation = reservationService.getCountUpcomingReservation();
        modelAndView.addObject("countUpcomingReservation", countUpcomingReservation);
        int countCompletedReservation = reservationService.getCountCompletedReservation();
        modelAndView.addObject("countCompletedReservation", countCompletedReservation);
        modelAndView.addObject("url", url);
        reservationService.statusReservationsUpdate();
        reservationService.statusCellsUpdateEmpty();
        return super.view("/view/table/reservationTable",
                "reservations", reservations,
                "pageNumbers", pageNumbers,
                "allRealInvoice", allRealInvoice,
                "countActiveReservation", countActiveReservation,
                "countUpcomingReservation", countUpcomingReservation,
                "countCompletedReservation", countCompletedReservation,
                "url", url);
    }

    @GetMapping("/view/add/reservationAdd")
    public ModelAndView reservationAdd(ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        ReservationDTO reservationDTO = new ReservationDTO();

        List<Client> allClients = clientService.findAll();
        List<Dog> allDogsInCatalog = dogService.findAll();
        List<Dog> allActiveReservedDogs = reservationService.findActiveReservedDogs();
        List<Dog> allDogs = new ArrayList<>(allDogsInCatalog);
        for (Dog dog : allDogsInCatalog) {
            Long idDog = dog.getId();
            for (Dog allActiveReservedDog : allActiveReservedDogs) {
                Long idActivDog = allActiveReservedDog.getId();
                if(idDog.intValue()==idActivDog.intValue()){
                    allDogs.remove(dog);
                    break;
                }
            }
        }

        List<Cell> allEmptyCells = cellService.findAllEmptyCells();

        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));

        Price price = allPrices.get();
        modelAndView.addObject("allDogs", allDogs);
        modelAndView.addObject("reservationDTO", reservationDTO);
        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("allEmptyCells", allEmptyCells);
        modelAndView.addObject("price", price);


        return super.view("/view/add/reservationAdd",
                "reservationDTO", reservationDTO,
                "allClients", allClients,
                "allEmptyCells", allEmptyCells,
                "price", price,
                "allDogs", allDogs);

    }
    @GetMapping("/view/add/reservationAdd/{id}")
    public ModelAndView reservationAddOnClientTable(@PathVariable("id") Long id,ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setClient(clientRepository.getOne(id));
        List<Client> allClients = clientService.findAll();
        List<Dog> allDogsInCatalog = dogService.findAll();
        List<Dog> allActiveReservedDogs = reservationService.findActiveReservedDogs();
        List<Dog> allDogs = new ArrayList<>(allDogsInCatalog);
        for (Dog dog : allDogsInCatalog) {
            Long idDog = dog.getId();
            for (Dog allActiveReservedDog : allActiveReservedDogs) {
                Long idActivDog = allActiveReservedDog.getId();
                if(idDog.intValue()==idActivDog.intValue()){
                    allDogs.remove(dog);
                    break;
                }
            }
        }

        List<Cell> allEmptyCells = cellService.findAllEmptyCells();

        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));

        Price price = allPrices.get();
        modelAndView.addObject("allDogs", allDogs);
        modelAndView.addObject("reservationDTO", reservationDTO);
        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("allEmptyCells", allEmptyCells);
        modelAndView.addObject("price", price);


        return super.view("/view/add/reservationAdd",
                "reservationDTO", reservationDTO,
                "allClients", allClients,
                "allEmptyCells", allEmptyCells,
                "price", price,
                "allDogs", allDogs);

    }

    @PostMapping("/view/add/reservationAdd")
    public ModelAndView addReservation(@Valid ReservationDTO reservationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            List<Dog> allDogsInCatalog = dogService.findAll();
            List<Dog> allActiveReservedDogs = reservationService.findActiveReservedDogs();
            List<Dog> allDogs = new ArrayList<>(allDogsInCatalog);
            for (Dog dog : allDogsInCatalog) {
                Long idDog = dog.getId();
                for (Dog allActiveReservedDog : allActiveReservedDogs) {
                    Long idActivDog = allActiveReservedDog.getId();
                    if(idDog.intValue()==idActivDog.intValue()){
                        allDogs.remove(dog);
                        break;
                    }
                }
            }
            Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));

            Price price = allPrices.get();
            List<Client> allClients = dogService.getAllClients();
            List<Cell> allEmptyCells = cellService.findAllEmptyCells();
            redirectAttributes.addFlashAttribute("reservationDTO", reservationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.reservationDTO", bindingResult);

            redirectAttributes.addFlashAttribute("allDogs", allDogs);
            redirectAttributes.addFlashAttribute("price", price);
            redirectAttributes.addFlashAttribute("allClients", allClients);
            return super.view("/view/add/reservationAdd",
                    "reservationDTO", reservationDTO,
                    "allClients", allClients,
                    "allEmptyCells", allEmptyCells,
                    "price", price,
                    "allDogs", allDogs);

        }

        reservationService.addReservation(reservationDTO, session);
        return super.redirect("/view/table/reservationTable");

    }

    @GetMapping("view/table/reservation/remove/{id}")
    public String removeReservation(@PathVariable Long id) {
        //set Cell Empty
        reservationService.setCellEmptyByReservationID(id);
        //delete reservation
        reservationService.removeReservationById(id);

        return "redirect:/view/table/reservationTable";
    }

    //Create Invoice
    @GetMapping("view/table/invoice/add/{id}")
    public String createInvoice(@PathVariable Long id, HttpSession session) {
        if(checkValidSession()) {
            return "redirect:/view/login";
        }
        Optional<Reservation> optionalReservation = reservationService.findById(id);
        Reservation reservation = optionalReservation.get();
        invoiceService.addInvoice(reservation, session);

        return "redirect:/view/table/invoiceTable";
    }

    @GetMapping("view/table/reservation/view/{id}")
    public ModelAndView getReservationView(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Reservation reservation = reservationService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<Invoice> allRealInvoice = invoiceService.findAllRealInvoice();
        modelAndView.addObject("reservation", reservation);
        modelAndView.addObject("allRealInvoice", allRealInvoice);


        return super.view("/view/table/reservationView", "reservation", reservation,"allRealInvoice", allRealInvoice);

    }

    @GetMapping("view/table/reservation/edit/{id}")
    public ModelAndView getReservationDetail(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (reservationService.checkReservationIsInvoised(id)){
             return super.redirect("/view/table/reservationTable");
         }

        //set Cell Empty
        reservationService.setCellEmptyByReservationID(id);
        //
        Reservation reservationDTO = reservationService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));

        List<Client> allClients = clientService.findAll();

        List<Dog> allDogsOnClient = new ArrayList<>();

        Integer weightDog = reservationDTO.getDog().getWeight();
        List<Cell> allEmptyCells = cellService.findAllEmptyCellsForCurrentDog(weightDog);
        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));
        Price price = allPrices.get();
        List<Dog> allDogsInCatalog = dogService.findAll();
        List<Dog> allActiveReservedDogs = reservationService.findActiveReservedDogs();

        List<Dog> allDogs = new ArrayList<>(allDogsInCatalog);
        for (Dog dog : allDogsInCatalog) {
            Long idDog = dog.getId();
            for (Dog allActiveReservedDog : allActiveReservedDogs) {
                Long idActivDog = allActiveReservedDog.getId();
                if(idDog.intValue()==idActivDog.intValue()){
                    allDogs.remove(dog);
                    break;
                }
            }
        }
        modelAndView.addObject("reservationDTO", reservationDTO);
        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("allEmptyCells", allEmptyCells);
        modelAndView.addObject("price", price);
        modelAndView.addObject("allDogs", allDogs);
        return super.view("/view/edit/reservationEdit",
                "reservationDTO", reservationDTO,
                "allClients", allClients,
                "allEmptyCells", allEmptyCells,
                "price", price,
                "allDogs", allDogs);

    }






    @PostMapping("view/table/reservation/edit/{id}/edit")
    public String editReservation(@PathVariable("id") Long id, ReservationEditDTO reservationEditDTO, HttpSession session) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return "redirect:/view/login";
        }

        reservationService.editReservation(id, reservationEditDTO, session);


        return "redirect:/view/table/reservationTable";
    }

    @RequestMapping(path = {"/", "/view/table/searchReservationNumber"})
    public ModelAndView searchReservationNumber(ModelAndView modelAndView, @RequestParam("reservationNumber") String reservationNumber) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Reservation> reservations = new ArrayList<>();
        if (!reservationNumber.equals("")) {
            reservations = reservationService.listReservationById(Long.parseLong(reservationNumber));
            modelAndView.addObject("reservations", reservations);
        } else {
            reservations = reservationService.findAllReservationByDesc();
            modelAndView.addObject("reservations", reservations);
        }
        return super.view("/view/table/reservationTable", "reservations", reservations);
    }

    @RequestMapping(path = {"/", "/view/table/searchReservationByClientEmail"})
    public ModelAndView searchClientEmail(ModelAndView modelAndView, @RequestParam("clientEmail") String clientEmail) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Reservation> reservations = new ArrayList<>();
        if (!clientEmail.equals("")) {
            reservations = reservationService.listReservationByClientEmail(clientEmail);
            modelAndView.addObject("reservations", reservations);
        } else {
            reservations = reservationService.findAllReservationByDesc();
            modelAndView.addObject("reservations", reservations);
        }
        return super.view("/view/table/reservationTable", "reservations", reservations);
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
