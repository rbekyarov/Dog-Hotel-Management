package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.entity.Company;
import rbekyarov.project.models.dto.CompanyDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.service.CityService;
import rbekyarov.project.service.CompanyService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CompanyController extends BaseController {
    private final CompanyService companyService;
    private final CityService cityService;
    private final HttpSession session;
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image";

    public CompanyController(CompanyService companyService, CityService cityService, HttpSession session) {
        this.companyService = companyService;
        this.cityService = cityService;
        this.session = session;
    }


    @GetMapping("view/MyCompany")
    public ModelAndView getMyCompany( ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Company> allCompany = companyService.findAllCompany();
        Company myCompany = allCompany.get(0);

        modelAndView.addObject("myCompany", myCompany);

        return super.view("view/MyCompany", "myCompany", myCompany);

    }
    @GetMapping("view/MyCompanyUser")
    public ModelAndView getMyCompanyUser( ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Company> allCompany = companyService.findAllCompany();
        Company myCompany = allCompany.get(0);

        modelAndView.addObject("myCompany", myCompany);

        return super.view("view/MyCompanyUser", "myCompany", myCompany);

    }

    @GetMapping("/view/edit/companyEdit")
    public ModelAndView getMyCompanyEdit(ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Long id = 1L;
        Company companyDTO =
                companyService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<City> allCity = cityService.findAllCityById();

        modelAndView.addObject("allCity", allCity);
        modelAndView.addObject("companyDTO", companyDTO);

        return super.view("/view/edit/companyEdit", "companyDTO", companyDTO,"allCity", allCity);

    }

    @PostMapping("/view/edit/companyEdit")
    public ModelAndView editCompany(@Valid CompanyDTO companyDTO, BindingResult bindingResult,
                              ModelAndView modelAndView ,@RequestParam("fileImage") MultipartFile file,
                              @RequestParam("imgName")String imgName) throws IOException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
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

        if (bindingResult.hasErrors()) {
            List<City> allCity = cityService.findAllCityById();
            modelAndView.addObject("companyDTO", companyDTO);
            companyDTO.setLogoName(imageUUID);
            return super.view("view/edit/companyEdit","companyDTO", companyDTO,"allCity", allCity);

        }



        companyService.editCompany(companyDTO.getName(),
                imageUUID,
                companyDTO.getCountry(),
                companyDTO.getCity().getId(),
                companyDTO.getAddress(),
                companyDTO.getVatNumber(),
                companyDTO.getEmail(),
                companyDTO.getBankName(),
                companyDTO.getBankAccount(),
                companyDTO.getBalance(),
                companyDTO.getManagerName(),
                id);
        return super.redirect("/view/MyCompany");
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
