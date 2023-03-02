package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rbekyarov.project.models.dto.VendorDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.Vendor;
import rbekyarov.project.repository.CostRepository;
import rbekyarov.project.repository.VendorRepository;
import rbekyarov.project.service.VendorService;
import rbekyarov.project.models.dto.VendorEditDTO;
import rbekyarov.project.service.CityService;
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
public class VendorController extends BaseController {
    private final VendorService vendorService;
    private final CityService cityService;
    private final VendorRepository vendorRepository;
    private final CostRepository costRepository;
    private final HttpSession session;

    public VendorController(VendorService vendorService, CityService cityService,
                            VendorRepository vendorRepository, CostRepository costRepository, HttpSession session) {
        this.vendorService = vendorService;
        this.cityService = cityService;
        this.vendorRepository = vendorRepository;
        this.costRepository = costRepository;
        this.session = session;
    }

    @GetMapping("/view/table/vendorTable")
    public ModelAndView vendorTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Vendor> vendors = vendorService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = vendors.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("vendors", vendors);
        return super.view("/view/table/vendorTable", "vendors", vendors,"pageNumbers", pageNumbers);
    }

    @GetMapping("/view/add/vendorAdd")
    public ModelAndView vendorAdd(ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        VendorDTO vendorDTO = new VendorDTO();
        List<City> allCity = cityService.findAllCityById();
        modelAndView.addObject("vendorDTO", vendorDTO);
        modelAndView.addObject("allCity", allCity);

        return super.view("/view/add/vendorAdd", "vendorDTO", vendorDTO, "allCity", allCity);
    }

    @PostMapping("/view/add/vendorAdd")
    public ModelAndView addVendor(@Valid VendorDTO vendorDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) throws IOException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("vendorDTO", vendorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vendorDTO", bindingResult);
            List<City> allCity = cityService.findAllCityById();
            return super.view("/view/add/vendorAdd", "vendorDTO", vendorDTO, "allCity", allCity);
        }
        vendorService.addVendor(vendorDTO, session);
        return super.redirect("/view/table/vendorTable");

    }

    @GetMapping("view/table/vendor/remove/{id}")
    public ModelAndView removeCity(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        boolean isUsed = false;
        List<Vendor> vendors = costRepository.listUsedVendor();
        for (Vendor b : vendors) {
            if(Objects.equals(b.getId(), id)){
                redirectAttributes.addFlashAttribute("isUsed", true);
                isUsed =true;
            }
        }
        if(!isUsed){
            vendorRepository.deleteById(id);
        }
        return super.redirect("/view/table/vendorTable");
    }

    @GetMapping("/view/table/vendor/edit/{id}")
    public ModelAndView getVendorDetail(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Vendor vendorEditDTO = vendorService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<City> allCity = cityService.findAllCityById();
        modelAndView.addObject("vendorEditDTO", vendorEditDTO);
        modelAndView.addObject("allCity", allCity);
        return super.view("/view/edit/vendorEdit", "vendorEditDTO", vendorEditDTO, "allCity",allCity);
    }

    @PostMapping("/view/table/vendor/edit/{id}")
    public ModelAndView editVendor(@PathVariable("id") Long id,
                             @Valid  VendorEditDTO vendorEditDTO, BindingResult bindingResult,
                             HttpSession session, ModelAndView modelAndView) throws ObjectNotFoundException, IOException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            List<City> allCity = cityService.findAllCityById();
            modelAndView.addObject("vendorEditDTO", vendorEditDTO);
            return super.view("/view/edit/vendorEdit", "vendorEditDTO", vendorEditDTO, "allCity",allCity);

        }
        vendorService.editVendor(vendorEditDTO.getName(),
                vendorEditDTO.getCountry(),
                vendorEditDTO.getCity().getId(),
                vendorEditDTO.getAddress(),
                vendorEditDTO.getVatNumber(),
                vendorEditDTO.getEmail(),
                session,
                id);
        return super.redirect("/view/table/vendorTable");
    }

    @RequestMapping(path = {"/","/view/table/searchVendorName"})
    public ModelAndView search(ModelAndView modelAndView,@RequestParam("vendorName") String vendorName) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Vendor> vendors = new ArrayList<>();
        if(!vendorName.equals("")) {
            vendors = vendorService.listVendorByName(vendorName);
            modelAndView.addObject("vendors", vendors);
        }else {
            vendors = vendorService.findAllVendor();
            modelAndView.addObject("vendors", vendors);}
        return super.view("/view/table/vendorTable", "vendors", vendors);
    }
    @RequestMapping(path = {"/","/view/table/searchVatNumber"})
    public ModelAndView searchClientEmail(ModelAndView modelAndView,@RequestParam("vendorVatNumber") String vendorVatNumber) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Vendor> vendors = new ArrayList<>();
        if(!vendorVatNumber.equals("")) {
            vendors = vendorService.listVendorByVatNumber(vendorVatNumber);
            modelAndView.addObject("vendors", vendors);
        }else {
            vendors = vendorService.findAllVendor();
            modelAndView.addObject("vendors", vendors);}
        return super.view("/view/table/vendorTable", "vendors", vendors);
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
