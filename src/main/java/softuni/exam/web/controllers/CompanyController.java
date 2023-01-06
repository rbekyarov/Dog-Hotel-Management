package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.CompanyDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Company;
import softuni.exam.service.CityService;
import softuni.exam.service.CompanyService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CompanyController extends BaseController {
    private final CompanyService companyService;
    private final CityService cityService;
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image";

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
    @GetMapping("view/MyCompanyUser")
    public ModelAndView getMyCompanyUser( ModelAndView modelAndView) {

        List<Company> allCompany = companyService.findAllCompany();
        Company myCompany = allCompany.get(0);

        modelAndView.addObject("myCompany", myCompany);

        return super.view("view/MyCompanyUser", "myCompany", myCompany);

    }

    @GetMapping("/view/edit/companyEdit")
    public ModelAndView getMyCompanyEdit(ModelAndView modelAndView) throws ObjectNotFoundException {
        Long id = 1L;
        var companyDTO =
                companyService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);
        modelAndView.addObject("companyDTO", companyDTO);

        return super.view("/view/edit/companyEdit", "companyDTO", companyDTO,"allCity", allCity);

    }

    @PostMapping("/view/edit/companyEdit")
    public String editCompany(CompanyDTO companyDTO, @RequestParam("fileImage") MultipartFile file,
                              @RequestParam("imgName")String imgName) throws IOException {

        Long id = 1L;
        //image upload
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else {
            imageUUID = imgName;
        }
        companyDTO.setLogoName(imageUUID);

        companyService.editCompany(companyDTO.getName(),
                companyDTO.getCountry(),
                imageUUID,
                companyDTO.getCity().getId(),
                companyDTO.getAddress(),
                companyDTO.getVatNumber(),
                companyDTO.getEmail(),
                companyDTO.getBankName(),
                companyDTO.getBankAccount(),
                companyDTO.getBalance(),
                companyDTO.getManagerName(),
                id);

        return "redirect:/view/MyCompany";
    }
}
