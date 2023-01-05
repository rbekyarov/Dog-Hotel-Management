package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.BreedDTO;
import softuni.exam.models.dto.BreedEditDTO;
import softuni.exam.models.entity.Breed;
import softuni.exam.service.BreedService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BreedController extends BaseController {
    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }


    @GetMapping("/view/table/breedTable")
    public ModelAndView breedTable(ModelAndView modelAndView) {

        List<Breed> breeds = breedService.findAllBreedById();
        modelAndView.addObject("breeds", breeds);
        return super.view("/view/table/breedTable", "breeds", breeds);
    }

    @GetMapping("/view/add/breedAdd")
    public ModelAndView breedAdd(ModelAndView modelAndView) {
        BreedDTO breedDTO = new BreedDTO();

        modelAndView.addObject("breedDTO", breedDTO);


        return super.view("/view/add/breedAdd", "breedDTO", breedDTO);

    }

    @PostMapping("/view/add/breedAdd")
    public String addBreed(@Valid BreedDTO breedDTO, HttpSession session) {

        breedService.addBreeds(breedDTO,session);

        return "redirect:/view/table/breedTable";
    }

    @GetMapping("view/table/breed/remove/{id}")
    public String removeBreed(@PathVariable Long id) {
        breedService.removeBreedById(id);

        return "redirect:/view/table/breedTable";
    }


    @GetMapping("view/table/breed/edit/{id}")
    public ModelAndView getBreedDetail(@PathVariable("id") Long id,
                                       ModelAndView modelAndView) throws ObjectNotFoundException {

        var breedDto =
                breedService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("breedDTO", breedDto);

        return super.view("/view/edit/breedEdit", "breedDTO", breedDto);

    }

    @PostMapping("view/table/breed/edit/{id}/edit")
    public String editBreed(@PathVariable("id") Long id, BreedEditDTO breedEditDTO,HttpSession session) throws ObjectNotFoundException {
        var breedDto =
                breedService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        breedService.editBreeds(breedEditDTO.getBreedName(), id,session);

        return "redirect:/view/table/breedTable";
    }
}
