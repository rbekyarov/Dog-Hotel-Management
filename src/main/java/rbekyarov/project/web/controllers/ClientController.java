package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rbekyarov.project.models.dto.ClientEditDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.dto.ClientDTO;
import rbekyarov.project.models.entity.Invoice;
import rbekyarov.project.repository.ClientRepository;
import rbekyarov.project.repository.InvoiceRepository;
import rbekyarov.project.repository.ReservationRepository;
import rbekyarov.project.service.CityService;
import rbekyarov.project.service.ClientService;
import rbekyarov.project.service.DogService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
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
    private final InvoiceRepository invoiceRepository;
    private final HttpSession session;

    public ClientController(ClientService clientService, DogService dogService, CityService cityService,
                            ClientRepository clientRepository,
                            ReservationRepository reservationRepository, InvoiceRepository invoiceRepository, HttpSession session) {
        this.clientService = clientService;
        this.dogService = dogService;
        this.cityService = cityService;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
        this.invoiceRepository = invoiceRepository;
        this.session = session;
    }


    @GetMapping("/view/table/clientTable")
    public ModelAndView clientTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Client> clients = clientService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        String topClientSting = invoiceRepository.getTop3Client().get(0);
        String topClient = topClientSting.replace(",", " - ");
        int totalPages = clients.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
            modelAndView.addObject("topClient", topClient);
        }
        modelAndView.addObject("clients", clients);
        return super.view("/view/table/clientTable", "clients", clients,"pageNumbers", pageNumbers,"topClient", topClient);
    }

    @GetMapping("/view/add/clientAdd")
    public ModelAndView clientAdd(ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        ClientDTO clientDTO = new ClientDTO();

        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);
        modelAndView.addObject("clientDTO", clientDTO);

        return super.view("/view/add/clientAdd",
                "clientDTO", clientDTO,
                "allCity", allCity);

    }

    @PostMapping("/view/add/clientAdd")
    public ModelAndView addClient(@Valid ClientDTO clientDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            List<City> allCity = cityService.findAllCityById();

            redirectAttributes.addFlashAttribute("clientDTO", clientDTO);
            redirectAttributes.addFlashAttribute("allCity", allCity);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.clientDTO", bindingResult);

            return super.view("/view/add/clientAdd",
                    "clientDTO", clientDTO,
                    "allCity", allCity);
        }
        clientService.addClient(clientDTO,session);
        return super.redirect("/view/table/clientTable");

    }

    @GetMapping("view/table/client/remove/{id}")
    public ModelAndView removeCity(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
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

    @GetMapping("/view/table/client/edit/{id}")
    public ModelAndView getClientDetail(@PathVariable("id") Long id,
                                        ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Client clientEditDTO =
                clientService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("clientEditDTO", clientEditDTO);
        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);


        return super.view("/view/edit/clientEdit",
                "clientEditDTO", clientEditDTO,
                "allCity", allCity);

    }

    @PostMapping("/view/table/client/edit/{id}")
    public ModelAndView clientEdit(@PathVariable("id") Long id,
                             @Valid ClientEditDTO clientEditDTO,
                             BindingResult bindingResult,
                             HttpSession session,ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            List<City> allCity = cityService.findAllCityById();
            modelAndView.addObject("clientEditDTO", clientEditDTO);
            return super.view("view/edit/clientEdit","clientEditDTO", clientEditDTO, "allCity", allCity);

        }

        //Set<Dog> dogs = dogService.findAllDogByClient(id);
        clientService.editClient(clientEditDTO.getFirstName(),
                clientEditDTO.getLastName(),
                clientEditDTO.getEmail(),
                clientEditDTO.getPhone(),
                clientEditDTO.getAddress(),
                clientEditDTO.getClientType(),
                clientEditDTO.getCity().getId(),
                id,
                session);
        return super.redirect("/view/table/clientTable");
    }
    @GetMapping("/view/table/client/view/{id}")
    public ModelAndView getClientView(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Client client = clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
       String clientName = String.format(client.getFirstName() +" "+ client.getLastName());
       List <Invoice> clientInvoice = invoiceRepository.getInvoicesOnClient(clientName);

       BigDecimal totalInvoicedMoney = invoiceRepository.getTotalInvoicedMoneyOnClient(clientName);
       int totalCountReservation = reservationRepository.getTotalCountReservationOnClient(id);
       int totalCountClientInvoice = clientInvoice.size();
        modelAndView.addObject("client", client);
        modelAndView.addObject("clientInvoice", clientInvoice);

        modelAndView.addObject("totalInvoicedMoney", totalInvoicedMoney);
        modelAndView.addObject("totalCountReservation", totalCountReservation);
        modelAndView.addObject("totalCountClientInvoice", totalCountClientInvoice);


        return super.view("/view/table/clientView",
                "client", client,
                "clientInvoice", clientInvoice,
                "totalInvoicedMoney", totalInvoicedMoney,
                "totalCountReservation", totalCountReservation,
                "totalCountClientInvoice", totalCountClientInvoice);

    }


    @RequestMapping(path = {"/","/view/table/searchClientByPhone"})
    public ModelAndView search(ModelAndView modelAndView,@RequestParam("clientPhone") String clientPhone) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
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
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Client> clients = new ArrayList<>();
        if(!clientEmail.equals("")) {
            clients = clientService.listClientByEmail(clientEmail);
            modelAndView.addObject("clients", clients);
        }else {
            clients = clientService.findAllClientByDesc();
            modelAndView.addObject("clients", clients);}
        return super.view("/view/table/clientTable", "clients", clients);
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
