package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rbekyarov.project.models.dto.DogEditDTO;
import rbekyarov.project.models.entity.*;
import rbekyarov.project.models.dto.DogDTO;
import rbekyarov.project.repository.ClientRepository;
import rbekyarov.project.repository.DogRepository;
import rbekyarov.project.repository.ReservationRepository;
import rbekyarov.project.service.DogService;
import rbekyarov.project.service.FileStorageService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class DogController extends BaseController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image";

    private final DogService dogService;
    private final DogRepository dogRepository;
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final HttpSession session;


    public DogController(DogService dogService, FileStorageService fileStorageService,
                         DogRepository dogRepository, ReservationRepository reservationRepository, ClientRepository clientRepository, ReservationRepository reservationRepository1, ClientRepository clientRepository1, HttpSession session) {

        this.dogService = dogService;
        this.dogRepository = dogRepository;
        this.reservationRepository = reservationRepository1;
        this.clientRepository = clientRepository1;
        this.session = session;
    }

    @GetMapping("/view/table/dogTable")
    public ModelAndView dogTable(ModelAndView  modelAndView , @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Dog> dogs = dogService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        List<Reservation> allActiveReservation = reservationRepository.findAllActiveReservation();

        int totalPages = dogs.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("dogs", dogs);
        modelAndView.addObject("allActiveReservation", allActiveReservation);
        dogService.updateDogYears();
        return super.view("/view/table/dogTable", "dogs", dogs,"pageNumbers", pageNumbers,"allActiveReservation", allActiveReservation);
    }

    @GetMapping("/view/add/dogAdd")
    public ModelAndView dogAdd(ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        DogDTO dogDTO = new DogDTO();


        List<Behavior> allBehaviors = dogService.getAllBehaviors();
        List<Breed> allBreeds = dogService.getAllBreeds();
        List<Client> allClients = dogService.getAllClients();
        modelAndView.addObject("dogDTO", dogDTO);

        modelAndView.addObject("allBehaviors", allBehaviors);
        modelAndView.addObject("allBreeds", allBreeds);
        modelAndView.addObject("allClients", allClients);

        return super.view("/view/add/dogAdd",
                "dogDTO", dogDTO,
                "allBehaviors",
                allBehaviors,
                "allBreeds",
                allBreeds,
                "allClients",
                allClients);
    }
    @GetMapping("/view/add/dogAdd/{id}")
    public ModelAndView dogAddOnClientTable(@PathVariable("id") Long id, ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        DogDTO dogDTO = new DogDTO();
        dogDTO.setClient(clientRepository.getOne(id));
        List<Behavior> allBehaviors = dogService.getAllBehaviors();
        List<Breed> allBreeds = dogService.getAllBreeds();
        List<Client> allClients = dogService.getAllClients();
        modelAndView.addObject("dogDTO", dogDTO);

        modelAndView.addObject("allBehaviors", allBehaviors);
        modelAndView.addObject("allBreeds", allBreeds);
        modelAndView.addObject("allClients", allClients);

        return super.view("/view/add/dogAdd",
                "dogDTO", dogDTO,
                "allBehaviors",
                allBehaviors,
                "allBreeds",
                allBreeds,
                "allClients",
                allClients);
    }

    @PostMapping("/view/add/dogAdd")
    public ModelAndView addDog(@Valid DogDTO dogDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                         @RequestParam("fileImage") MultipartFile file,
                         @RequestParam("imgName") String imgName, HttpSession session) throws IOException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            List<Behavior> allBehaviors = dogService.getAllBehaviors();
            List<Breed> allBreeds = dogService.getAllBreeds();
            List<Client> allClients = dogService.getAllClients();

            redirectAttributes.addFlashAttribute("dogDTO", dogDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.clientDTO", bindingResult);

            redirectAttributes.addFlashAttribute("allBehaviors", allBehaviors);
            redirectAttributes.addFlashAttribute("allBreeds", allBreeds);
            redirectAttributes.addFlashAttribute("allClients", allClients);
            return super.view("/view/add/dogAdd",
                    "dogDTO", dogDTO,
                    "allBehaviors",
                    allBehaviors,
                    "allBreeds",
                    allBreeds,
                    "allClients",
                    allClients);
        }

        dogService.addDog(dogDTO, file, imgName, session);
        return super.redirect("/view/table/dogTable");

    }

    @GetMapping("view/table/dog/remove/{id}")
    public ModelAndView removeDog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        boolean isUsed = false;
        List<Dog> dogs = reservationRepository.listUsedDog();
        for (Dog b : dogs) {
            if(Objects.equals(b.getId(), id)){
                redirectAttributes.addFlashAttribute("isUsed", true);
                isUsed =true;
            }
        }
        if(!isUsed){
            dogService.removeDogById(id);
        }
        return super.redirect("/view/table/dogTable");
    }

    @GetMapping("/view/table/dog/edit/{id}")
    public ModelAndView getDogDetail(@PathVariable("id") Long id,
                                     ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Dog dogEditDTO =
                dogService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<Behavior> allBehaviors = dogService.getAllBehaviors();
        List<Breed> allBreeds = dogService.getAllBreeds();
        List<Client> allClients = dogService.getAllClients();
        modelAndView.addObject("allBehaviors", allBehaviors);
        modelAndView.addObject("allBreeds", allBreeds);
        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("dogEditDTO", dogEditDTO);


        return super.view("/view/edit/dogEdit",
                "dogEditDTO", dogEditDTO,
                "allBehaviors",
                allBehaviors,
                "allBreeds",
                allBreeds,
                "allClients",
                allClients
        );

    }

    @PostMapping("/view/table/dog/edit/{id}")
    public ModelAndView editDog(@PathVariable("id") Long id,
                          @Valid DogEditDTO dogEditDTO,BindingResult bindingResult,
                          @RequestParam("fileImage") MultipartFile file,
                          @RequestParam("imgName") String imgName,
                          HttpSession session, ModelAndView modelAndView) throws ObjectNotFoundException, IOException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            dogEditDTO.setImageName(imgName);
            List<Behavior> allBehaviors = dogService.getAllBehaviors();
            List<Breed> allBreeds = dogService.getAllBreeds();
            List<Client> allClients = dogService.getAllClients();
            modelAndView.addObject("dogEditDTO", dogEditDTO);

            return super.view("/view/edit/dogEdit",
                    "dogEditDTO", dogEditDTO,
                    "allBehaviors",
                    allBehaviors,
                    "allBreeds",
                    allBreeds,
                    "allClients",
                    allClients
            );

        }

        dogService.editDog(id, dogEditDTO, imgName, file, session);
        return super.redirect("/view/table/dogTable");

    }

    @RequestMapping(path = {"/","/view/table/searchDogName"})
    public ModelAndView search(ModelAndView modelAndView,@RequestParam("dogName") String dogName) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Dog> dogs = new ArrayList<>();
        if(!dogName.equals("")) {
            dogs = dogService.listDogByName(dogName);
            modelAndView.addObject("dogs", dogs);
        }else {
            dogs = dogService.findAllDogByDesc();
            modelAndView.addObject("dogs", dogs);}
        return super.view("/view/table/dogTable", "dogs", dogs);
    }
    @RequestMapping(path = {"/","/view/table/searchClientEmail"})
    public ModelAndView searchClientEmail(ModelAndView modelAndView,@RequestParam("clientEmail") String clientEmail) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Dog> dogs = new ArrayList<>();
        if(!clientEmail.equals("")) {
            dogs = dogService.listDogByClientEmail(clientEmail);
            modelAndView.addObject("dogs", dogs);
        }else {
            dogs = dogService.findAllDogByDesc();
            modelAndView.addObject("dogs", dogs);}
        return super.view("/view/table/dogTable", "dogs", dogs);
    }

    @GetMapping("view/table/dog/view/{id}")
    public ModelAndView getDogView(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        modelAndView.addObject("dog", dog);
        List<Reservation> reservations = reservationRepository.getReservationByDogId(id);
        modelAndView.addObject("reservations", reservations);
        return super.view("/view/table/dogView", "dog", dog,"reservations", reservations);

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
