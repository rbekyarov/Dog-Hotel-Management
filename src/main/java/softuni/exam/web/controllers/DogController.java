package softuni.exam.web.controllers;

import io.github.classgraph.Resource;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.DogDTO;
import softuni.exam.models.dto.DogEditDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Breed;
import softuni.exam.models.entity.Client;
import softuni.exam.models.entity.Dog;
import softuni.exam.service.DogService;
import softuni.exam.service.FileStorageService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class DogController extends BaseController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image";

    private final DogService dogService;


    public DogController(DogService dogService, FileStorageService fileStorageService) {

        this.dogService = dogService;
    }

    @GetMapping("/view/table/dogTable")
    public ModelAndView dogTable(ModelAndView modelAndView) {
        List<Dog> dogs = dogService.findAllDogById();
        modelAndView.addObject("dogs", dogs);
        return super.view("/view/table/dogTable", "dogs", dogs);
    }

    @GetMapping("/view/add/dogAdd")
    public ModelAndView dogAdd(ModelAndView modelAndView) {
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

    @PostMapping("/view/add/dogAdd")
    public String addDog(@Valid DogDTO dogDTO,
                         @RequestParam("fileImage") MultipartFile file,
                         @RequestParam("imgName")String imgName, HttpSession session) throws IOException {
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else {
            imageUUID = imgName;
        }
        dogDTO.setImageName(imageUUID);


        dogService.addDog(dogDTO,session);

        return "redirect:/view/table/dogTable";
    }

    @GetMapping("view/table/dog/remove/{id}")
    public String removeDog(@PathVariable Long id) {
        dogService.removeDogById(id);

        return "redirect:/view/table/dogTable";
    }

    @GetMapping("view/table/dog/edit/{id}")
    public ModelAndView getDogDetail(@PathVariable("id") Long id,
                                     ModelAndView modelAndView) throws ObjectNotFoundException {

        var dogDTO =
                dogService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<Behavior> allBehaviors = dogService.getAllBehaviors();
        List<Breed> allBreeds = dogService.getAllBreeds();
        List<Client> allClients = dogService.getAllClients();
        modelAndView.addObject("allBehaviors", allBehaviors);
        modelAndView.addObject("allBreeds", allBreeds);
        modelAndView.addObject("allClients", allClients);
        modelAndView.addObject("dogDTO", dogDTO);


        return super.view("/view/edit/dogEdit",
                "dogDTO", dogDTO,
                "allBehaviors",
                allBehaviors,
                "allBreeds",
                allBreeds,
                "allClients",
                allClients
        );

    }

    @PostMapping("view/table/dog/edit/{id}/edit")
    public String editDog(@PathVariable("id") Long id, DogEditDTO dogEditDTO, @RequestParam("fileImage") MultipartFile file,
                          @RequestParam("imgName")String imgName, HttpSession session) throws ObjectNotFoundException, IOException {
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else {
            imageUUID = imgName;
        }
        dogEditDTO.setImageName(imageUUID);
        dogService.editDog(dogEditDTO.getName(),
                dogEditDTO.getBirthDate(),
                dogEditDTO.getWeight(),
                dogEditDTO.getBreed().getId(),
                dogEditDTO.getSex(),
                dogEditDTO.getPassport(),
                dogEditDTO.getMicrochip(),
                dogEditDTO.getClient().getId(),
                dogEditDTO.getBehavior().getId(),
                imageUUID,
                id,
                session);

        return "redirect:/view/table/dogTable";
    }
}
