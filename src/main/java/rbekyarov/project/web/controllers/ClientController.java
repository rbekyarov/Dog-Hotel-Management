package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rbekyarov.project.models.dto.ClientEditDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.dto.ClientDTO;
import rbekyarov.project.repository.ClientRepository;
import rbekyarov.project.repository.ReservationRepository;
import rbekyarov.project.service.CityService;
import rbekyarov.project.service.ClientService;
import rbekyarov.project.service.DogService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ClientController extends BaseController {
    private final ClientService clientService;
    private final DogService dogService;
    private final CityService cityService;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;

    public ClientController(ClientService clientService, DogService dogService, CityService cityService,
                            ClientRepository clientRepository,
                            ReservationRepository reservationRepository) {
        this.clientService = clientService;
        this.dogService = dogService;
        this.cityService = cityService;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }


    @GetMapping("/view/table/clientTable")
    public ModelAndView clientTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Client> clients = clientService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = clients.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("clients", clients);
        return super.view("/view/table/clientTable", "clients", clients,"pageNumbers", pageNumbers);
    }

    @GetMapping("/view/add/clientAdd")
    public ModelAndView clientAdd(ModelAndView modelAndView) {
        ClientDTO clientDTO = new ClientDTO();

        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);
        modelAndView.addObject("clientDTO", clientDTO);

        return super.view("/view/add/clientAdd",
                "clientDTO", clientDTO,
                "allCity", allCity);

    }

    @PostMapping("/view/add/clientAdd")
    public String addClient(@Valid ClientDTO clientDTO, HttpSession session) {

        clientService.addClient(clientDTO,session);

        return "redirect:/view/table/clientTable";
    }

    @GetMapping("view/table/client/remove/{id}")
    public ModelAndView removeCity(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean isUsed = false;
        List<Client> clients = reservationRepository.listUsedClient();
        for (Client b : clients) {
            if(Objects.equals(b.getId(), id)){
                redirectAttributes.addFlashAttribute("isUsed", true);
                isUsed =true;
            }
        }
        if(!isUsed){
            clientRepository.deleteById(id);
        }
        return super.redirect("/view/table/clientTable");
    }

    @GetMapping("view/table/client/edit/{id}")
    public ModelAndView getClientDetail(@PathVariable("id") Long id,
                                        ModelAndView modelAndView) throws ObjectNotFoundException {

        Client clientDTO =
                clientService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("clientDTO", clientDTO);
        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);


        return super.view("/view/edit/clientEdit",
                "clientDTO", clientDTO,
                "allCity", allCity);

    }

    @PostMapping("view/table/client/edit/{id}/edit")
    public String clientEdit(@PathVariable("id") Long id, ClientEditDTO clientEditDTO, HttpSession session) throws ObjectNotFoundException {
        //Set<Dog> dogs = dogService.findAllDogByClient(id);
        clientService.editClient(clientEditDTO.getFirstName(),
                clientEditDTO.getLastName(),
                clientEditDTO.getEmail(),
                clientEditDTO.getPhone(),
                clientEditDTO.getAddress(),
                clientEditDTO.getCity().getId(),
                id,
                session);

        return "redirect:/view/table/clientTable";
    }
    @RequestMapping(path = {"/","/view/table/searchClientByPhone"})
    public ModelAndView search(ModelAndView modelAndView,@RequestParam("clientPhone") String clientPhone) {
        List<Client> clients = new ArrayList<>();
        if(!clientPhone.equals("")) {
            clients = clientService.listClientByPhone(clientPhone);
            modelAndView.addObject("clients", clients);
        }else {
            clients = clientService.findAllClientByDesc();
            modelAndView.addObject("clients", clients);}
        return super.view("/view/table/clientTable", "clients", clients);
    }
    @RequestMapping(path = {"/","/view/table/searchClientByEmail"})
    public ModelAndView searchClientEmail(ModelAndView modelAndView,@RequestParam("clientEmail") String clientEmail) {
        List<Client> clients = new ArrayList<>();
        if(!clientEmail.equals("")) {
            clients = clientService.listClientByEmail(clientEmail);
            modelAndView.addObject("clients", clients);
        }else {
            clients = clientService.findAllClientByDesc();
            modelAndView.addObject("clients", clients);}
        return super.view("/view/table/clientTable", "clients", clients);
    }
}
