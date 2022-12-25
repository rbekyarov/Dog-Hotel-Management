package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.ReservationDTO;
import softuni.exam.models.entity.*;
import softuni.exam.service.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReservationController extends BaseController {
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final CellService cellService;
    private final DogService dogService;
    private final PriceService priceService;

    public ReservationController(ReservationService reservationService, ClientService clientService, CellService cellService, DogService dogService, PriceService priceService) {

        this.reservationService = reservationService;
        this.clientService = clientService;
        this.cellService = cellService;
        this.dogService = dogService;
        this.priceService = priceService;
    }


    @GetMapping("/view/table/reservationTable")
    public ModelAndView reservationTable(ModelAndView modelAndView) {

        List<Reservation> reservations = reservationService.findAllReservationById();
        modelAndView.addObject("reservations", reservations);
        return super.view("/view/table/reservationTable", "reservations", reservations);
    }

    @GetMapping("/view/add/reservationAdd")
    public ModelAndView reservationAdd(ModelAndView modelAndView, @RequestParam(value = "id", required = false) String clientId) {
        ReservationDTO reservationDTO = new ReservationDTO();

        List<Client> allClients = clientService.findAllClientById();

        List<Cell> allEmptyCells = cellService.findAllEmptyCells();

        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("allEmptyCells", allEmptyCells);
        modelAndView.addObject("reservationDTO", reservationDTO);


        return super.view("/view/add/reservationAdd",
                "reservationDTO", reservationDTO,
                "allClients",
                allClients,
                "allEmptyCells",
                allEmptyCells
        );

    }

    @PostMapping("/view/add/reservationAdd")
    public String addReservation(@Valid ReservationDTO reservationDTO) {

        reservationService.addReservation(reservationDTO);

        return "redirect:/view/table/reservationTable";
    }

    @GetMapping("view/table/reservation/remove/{id}")
    public String removeReservation(@PathVariable Long id) {
        reservationService.removeReservationById(id);

        return "redirect:/view/table/reservationTable";
    }


    @GetMapping("view/table/reservation/edit/{id}")
    public ModelAndView getReservationDetail(@PathVariable("id") Long id,
                                             ModelAndView modelAndView) throws ObjectNotFoundException {

        var reservationDTO =
                dogService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<Client> allClients = clientService.findAllClientById();
        List<Cell> allEmptyCells = cellService.findAllEmptyCells();

        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("allEmptyCells", allEmptyCells);
        modelAndView.addObject("reservationDTO", reservationDTO);


        return super.view("/view/add/reservationAdd",
                "reservationDTO", reservationDTO,
                "allClients",
                allClients,
                "allEmptyCells",
                allEmptyCells
        );

    }

//    @PostMapping("view/table/reservation/edit/{id}/edit")
//    public String editReservation(@PathVariable("id") Long id , ReservationEditDTO reservationEditDTO) throws ObjectNotFoundException {
//        reservationService.editReservation(
//                reservationEditDTO.getClient().getId(),
//                reservationEditDTO.getDogs(),
//                reservationEditDTO.getStartDate(),
//                reservationEditDTO.getCells(),
//                reservationEditDTO.getFood(),
//                reservationEditDTO.getTraining(),
//                reservationEditDTO.getBathing(),
//                reservationEditDTO.getCombing(),
//                reservationEditDTO.getEars(),
//                reservationEditDTO.getPaws(),
//                reservationEditDTO.getNails(),
//                reservationEditDTO.getDiscount(),
//                id);
//
//        return "redirect:/view/table/reservationTable";
//    }
}
