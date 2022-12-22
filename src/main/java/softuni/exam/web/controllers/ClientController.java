package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.ClientDTO;
import softuni.exam.models.dto.ClientEditDTO;
import softuni.exam.models.entity.*;
import softuni.exam.service.CityService;
import softuni.exam.service.ClientService;
import softuni.exam.service.DogService;

import javax.validation.Valid;
import java.util.*;

@Controller
public class ClientController extends BaseController{
   private final ClientService clientService;
   private final DogService dogService;
   private final CityService cityService;

    public ClientController(ClientService clientService, DogService dogService, CityService cityService) {
        this.clientService = clientService;
        this.dogService = dogService;
        this.cityService = cityService;
    }


    @GetMapping("/view/table/clientTable")
    public ModelAndView clientTable(ModelAndView modelAndView) {

        List<Client> clients = clientService.findAllClientById();

        //dogService.findAllDogByClient(id)
        modelAndView.addObject("clients", clients);
        return super.view("/view/table/clientTable", "clients", clients);
    }

    @GetMapping("/view/add/clientAdd")
    public ModelAndView clientAdd(ModelAndView modelAndView) {
        ClientDTO clientDTO = new ClientDTO();

        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);
        modelAndView.addObject("clientDTO", clientDTO);

        return super.view("/view/add/clientAdd",
                "clientDTO",clientDTO,
                "allCity", allCity);

    }


    @PostMapping("/view/add/clientAdd")
    public String addClient(@Valid ClientDTO clientDTO) {

        clientService.addClient(clientDTO);

        return "redirect:/view/table/clientTable";
    }

    @GetMapping("view/table/client/remove/{id}")
    public String removeClient( @PathVariable Long id) {
        clientService.removeClientById(id);

        return "redirect:/view/table/clientTable";
    }


    @GetMapping("view/table/client/edit/{id}")
    public ModelAndView getClientDetail(@PathVariable("id") Long id,
                                 ModelAndView modelAndView) throws ObjectNotFoundException {

        var clientDTO =
                clientService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));


        modelAndView.addObject("clientDTO", clientDTO);
        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);


        return super.view("/view/edit/clientEdit",
                "clientDTO",clientDTO,
                "allCity", allCity);

    }

    @PostMapping("view/table/client/edit/{id}/edit")
    public String clientEdit(@PathVariable("id") Long id , ClientEditDTO clientEditDTO) throws ObjectNotFoundException {
       //Set<Dog> dogs = dogService.findAllDogByClient(id);
        clientService.editClient(clientEditDTO.getFirstName(),
                clientEditDTO.getLastName(),
                clientEditDTO.getEmail(),
                clientEditDTO.getPhone(),
                clientEditDTO.getAddress(),
                clientEditDTO.getCity().getId(),
                id);

        return "redirect:/view/table/clientTable";
    }
}
