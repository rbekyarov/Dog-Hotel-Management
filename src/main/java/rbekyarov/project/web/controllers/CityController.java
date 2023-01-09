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
import rbekyarov.project.models.dto.CityDTO;
import rbekyarov.project.models.dto.CityEditDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.service.CityService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CityController extends BaseController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/view/table/cityTable")
    public ModelAndView cityTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<City> cities = cityService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        int totalPages = cities.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("cities", cities);
        return super.view("/view/table/cityTable", "cities", cities,"pageNumbers", pageNumbers);
    }

    @GetMapping("/view/add/cityAdd")
    public ModelAndView cityAdd(ModelAndView modelAndView) {
        CityDTO cityDTO = new CityDTO();

        modelAndView.addObject("cityDTO", cityDTO);


        return super.view("/view/add/cityAdd", "cityDTO", cityDTO);

    }

    @PostMapping("/view/add/cityAdd")
    public String addCity(@Valid CityDTO cityDTO, HttpSession session) {

        cityService.addCity(cityDTO,session);

        return "redirect:/view/table/cityTable";
    }

    @GetMapping("view/table/city/remove/{id}")
    public String removeCity(@PathVariable Long id) {
        cityService.removeCityById(id);

        return "redirect:/view/table/cityTable";
    }


    @GetMapping("view/table/city/edit/{id}")
    public ModelAndView getCityDetail(@PathVariable("id") Long id,
                                      ModelAndView modelAndView) throws ObjectNotFoundException {

        var cityDTO =
                cityService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("cityDTO", cityDTO);

        return super.view("/view/edit/cityEdit", "cityDTO", cityDTO);

    }

    @PostMapping("view/table/city/edit/{id}/edit")
    public String editCity(@PathVariable("id") Long id, CityEditDTO cityEditDTO, HttpSession session) throws ObjectNotFoundException {
        var cityDto =
                cityService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        cityService.editCity(cityEditDTO.getCode(), id, cityEditDTO.getName(),session);

        return "redirect:/view/table/cityTable";
    }
}
