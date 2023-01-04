package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.dto.ReservationEditDTO;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CellRepository;
import softuni.exam.service.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController extends BaseController {
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final CellService cellService;
    private final DogService dogService;
    private final PriceService priceService;
    private final CellRepository cellRepository;

    public ReservationController(ReservationService reservationService, ClientService clientService, CellService cellService, DogService dogService, PriceService priceService, CellRepository cellRepository) {

        this.reservationService = reservationService;
        this.clientService = clientService;
        this.cellService = cellService;
        this.dogService = dogService;
        this.priceService = priceService;
        this.cellRepository = cellRepository;
    }


    @GetMapping("/view/table/reservationTable")
    public ModelAndView reservationTable(ModelAndView modelAndView) {

        List<Reservation> reservations = reservationService.findAllReservationById();
        modelAndView.addObject("reservations", reservations);
        return super.view("/view/table/reservationTable", "reservations", reservations);
    }

    @GetMapping("/view/add/reservationAdd")
    public ModelAndView reservationAdd(ModelAndView modelAndView) {
        ReservationDTO reservationDTO = new ReservationDTO();

        List<Client> allClients = clientService.findAll();

        List<Dog> allDogsOnClient = new ArrayList<>();


        List<Cell> allEmptyCells = cellService.findAllEmptyCells();
        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));
        Price price = allPrices.get();
        modelAndView.addObject("reservationDTO", reservationDTO);
        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("allEmptyCells", allEmptyCells);
        modelAndView.addObject("price", price);

        return super.view("/view/add/reservationAdd", "reservationDTO", reservationDTO, "allClients", allClients, "allEmptyCells", allEmptyCells, "price", price, "allDogsOnClient", allDogsOnClient

        );

    }

    @PostMapping("/view/add/reservationAdd")
    public String addReservation(@Valid ReservationDTO reservationDTO) {

        reservationService.addReservation(reservationDTO);

        return "redirect:/view/table/reservationTable";
    }

    @GetMapping("view/table/reservation/remove/{id}")
    public String removeReservation(@PathVariable Long id) {

        Optional<Reservation> reservationOptional = reservationService.findById(id);
        Reservation reservation = reservationOptional.get();
        Long cellId = reservation.getCell().getId();
        cellService.setCellEmpty(cellId);

        reservationService.removeReservationById(id);

        return "redirect:/view/table/reservationTable";
    }

    @GetMapping("view/table/reservation/view/{id}")
    public ModelAndView getReservationView(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {

        Reservation reservation = reservationService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        modelAndView.addObject("reservation", reservation);


        return super.view("/view/table/reservationView", "reservation", reservation);

    }

    @GetMapping("view/table/reservation/edit/{id}")
    public ModelAndView getReservationDetail(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {

        //set Cell Empy
        Optional<Reservation> reservationOptional = reservationService.findById(id);
        Reservation reservation = reservationOptional.get();
        Long cellId = reservation.getCell().getId();
        cellService.setCellEmpty(cellId);
         //
        var reservationDTO = reservationService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));

        List<Client> allClients = clientService.findAll();

        List<Dog> allDogsOnClient = new ArrayList<>();


        List<Cell> allEmptyCells = cellService.findAllEmptyCells();
        Optional<Price> allPrices = priceService.findById(Long.parseLong(Integer.toString(priceService.findAllPriceById().size())));
        Price price = allPrices.get();
        modelAndView.addObject("reservationDTO", reservationDTO);
        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("allEmptyCells", allEmptyCells);
        modelAndView.addObject("price", price);

        return super.view("/view/edit/reservationEdit", "reservationDTO", reservationDTO, "allClients", allClients, "allEmptyCells", allEmptyCells, "price", price, "allDogsOnClient", allDogsOnClient);
    }

    @PostMapping("view/table/reservation/edit/{id}/edit")
    public String editReservation(@PathVariable("id") Long id, ReservationEditDTO reservationEditDTO) throws ObjectNotFoundException {


        reservationService.editReservation(id, reservationEditDTO);


        return "redirect:/view/table/reservationTable";
    }
}
