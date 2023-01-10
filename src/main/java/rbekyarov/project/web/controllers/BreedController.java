package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.dto.BreedDTO;
import rbekyarov.project.models.dto.BreedEditDTO;
import rbekyarov.project.models.entity.Breed;
import rbekyarov.project.service.BreedService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BreedController extends BaseController {
    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }


    @GetMapping("/view/table/breedTable")
    public ModelAndView breedTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Breed> breeds = breedService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        int totalPages = breeds.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

        modelAndView.addObject("breeds", breeds);
        return super.view("/view/table/breedTable", "breeds", breeds,"pageNumbers", pageNumbers);
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

        Breed breedDto =
                breedService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("breedDTO", breedDto);

        return super.view("/view/edit/breedEdit", "breedDTO", breedDto);

    }

    @PostMapping("view/table/breed/edit/{id}/edit")
    public String editBreed(@PathVariable("id") Long id, BreedEditDTO breedEditDTO, HttpSession session) throws ObjectNotFoundException {
        breedService.editBreeds(breedEditDTO.getBreedName(), id,session);

        return "redirect:/view/table/breedTable";
    }
}
