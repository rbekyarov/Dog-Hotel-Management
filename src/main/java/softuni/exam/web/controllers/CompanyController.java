package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.CompanyDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Company;
import softuni.exam.service.CityService;
import softuni.exam.service.CompanyService;

import java.util.List;

@Controller
public class CompanyController extends BaseController {
    private final CompanyService companyService;
    private final CityService cityService;

    public CompanyController(CompanyService companyService, CityService cityService) {
        this.companyService = companyService;
        this.cityService = cityService;
    }


    @GetMapping("view/MyCompany")
    public ModelAndView getMyCompany( ModelAndView modelAndView) {

        List<Company> allCompany = companyService.findAllCompany();
        Company myCompany = allCompany.get(0);

        modelAndView.addObject("myCompany", myCompany);

        return super.view("view/MyCompany", "myCompany", myCompany);

    }

    @GetMapping("view/edit/companyEdit")
    public ModelAndView getMyCompanyEdit(ModelAndView modelAndView) throws ObjectNotFoundException {
        Long id = 1L;
        var companyDto =
                companyService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);
        modelAndView.addObject("companyDto", companyDto);

        return super.view("/view/edit/companyEdit", "companyDto", companyDto,"allCity", allCity);

    }

    @PostMapping("view/edit/companyEdit")
    public String editBreed(CompanyDTO companyDTO) {
        Long id = 1L;
        companyService.editCompany(companyDTO.getName(),
                companyDTO.getCountry(),
                companyDTO.getCity().getId(),
                companyDTO.getAddress(),
                companyDTO.getVatNumber(),
                companyDTO.getEmail(),
                companyDTO.getBankAccount(),
                companyDTO.getManagerName(),
                id);

        return "redirect:/view/MyCompany";
    }
}
