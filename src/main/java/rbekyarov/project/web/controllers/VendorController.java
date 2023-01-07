package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.dto.VendorDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.Vendor;
import rbekyarov.project.service.VendorService;
import rbekyarov.project.models.dto.VendorEditDTO;
import rbekyarov.project.service.CityService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class VendorController extends BaseController {
    private final VendorService vendorService;
    private final CityService cityService;

    public VendorController(VendorService vendorService, CityService cityService) {
        this.vendorService = vendorService;
        this.cityService = cityService;
    }

    @GetMapping("/view/table/vendorTable")
    public ModelAndView vendorTable(ModelAndView modelAndView) {
        List<Vendor> vendors = vendorService.findAllVendor();
        modelAndView.addObject("vendors", vendors);
        return super.view("/view/table/vendorTable", "vendors", vendors);
    }

    @GetMapping("/view/add/vendorAdd")
    public ModelAndView vendorAdd(ModelAndView modelAndView) {
        VendorDTO vendorDTO = new VendorDTO();
        List<City> allCity = cityService.findAllCityById();
        modelAndView.addObject("vendorDTO", vendorDTO);
        modelAndView.addObject("allCity", allCity);

        return super.view("/view/add/vendorAdd", "vendorDTO", vendorDTO, "allCity", allCity);
    }

    @PostMapping("/view/add/vendorAdd")
    public String addVendor(@Valid VendorDTO vendorDTO, HttpSession session) throws IOException {

        vendorService.addVendor(vendorDTO, session);

        return "redirect:/view/table/vendorTable";
    }

    @GetMapping("view/table/vendor/remove/{id}")
    public String removeVendor(@PathVariable Long id) {
        vendorService.removeVendorById(id);

        return "redirect:/view/table/vendorTable";
    }

    @GetMapping("view/table/vendor/edit/{id}")
    public ModelAndView getVendorDetail(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {

        var vendorDTO = vendorService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<City> allCity = cityService.findAllCityById();
        modelAndView.addObject("vendorDTO", vendorDTO);
        modelAndView.addObject("allCity", allCity);
        return super.view("/view/edit/vendorEdit", "vendorDTO", vendorDTO, "allCity",allCity);
    }

    @PostMapping("view/table/vendor/edit/{id}/edit")
    public String editVendor(@PathVariable("id") Long id, VendorEditDTO vendorEditDTO, HttpSession session) throws ObjectNotFoundException, IOException {

        vendorService.editVendor(vendorEditDTO.getName(),
                vendorEditDTO.getCountry(),
                vendorEditDTO.getCity().getId(),
                vendorEditDTO.getAddress(),
                vendorEditDTO.getVatNumber(),
                vendorEditDTO.getEmail(),
                session,
                id);

        return "redirect:/view/table/vendorTable";
    }
}
