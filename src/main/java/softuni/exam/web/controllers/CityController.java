package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.CityDTO;
import softuni.exam.models.dto.CityEditDTO;
import softuni.exam.models.entity.City;
import softuni.exam.service.CityService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CityController extends BaseController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/view/table/cityTable")
    public ModelAndView cityTable(ModelAndView modelAndView) {

        List<City> cities = cityService.findAllCityById();
        modelAndView.addObject("cities", cities);
        return super.view("/view/table/cityTable", "cities", cities);
    }

    @GetMapping("/view/add/cityAdd")
    public ModelAndView cityAdd(ModelAndView modelAndView) {
        CityDTO cityDTO = new CityDTO();

        modelAndView.addObject("cityDTO", cityDTO);


        return super.view("/view/add/cityAdd", "cityDTO", cityDTO);

    }

    @PostMapping("/view/add/cityAdd")
    public String addCity(@Valid CityDTO cityDTO) {

        cityService.addCity(cityDTO);

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
    public String editCity(@PathVariable("id") Long id, CityEditDTO cityEditDTO) throws ObjectNotFoundException {
        var cityDto =
                cityService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        cityService.editCity(cityEditDTO.getCode(), id, cityEditDTO.getName());

        return "redirect:/view/table/cityTable";
    }
}
