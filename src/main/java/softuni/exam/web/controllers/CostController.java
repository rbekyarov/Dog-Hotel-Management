package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.CostDTO;
import softuni.exam.models.dto.CostEditDTO;
import softuni.exam.models.dto.VendorDTO;
import softuni.exam.models.dto.VendorEditDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Cost;
import softuni.exam.models.entity.Vendor;
import softuni.exam.service.CostService;
import softuni.exam.service.VendorService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class CostController extends BaseController {
    private final CostService costService;
    private final VendorService vendorService;

    public CostController(CostService costService, VendorService vendorService) {
        this.costService = costService;
        this.vendorService = vendorService;
    }


    @GetMapping("/view/table/costTable")
    public ModelAndView costTable(ModelAndView modelAndView) {
        List<Cost> costs = costService.findAllCost();
        modelAndView.addObject("costs", costs);
        return super.view("/view/table/costTable", "costs", costs);
    }

    @GetMapping("/view/add/costAdd")
    public ModelAndView costAdd(ModelAndView modelAndView) {
        CostDTO costDTO = new CostDTO();
        List<Vendor> allVendor = vendorService.findAllVendor();
        modelAndView.addObject("costDTO", costDTO);
        modelAndView.addObject("allVendor", allVendor);

        return super.view("/view/add/costAdd", "costDTO", costDTO, "allVendor", allVendor);
    }

    @PostMapping("/view/add/costAdd")
    public String addCost(@Valid CostDTO costDTO, HttpSession session) throws IOException {

        costService.addCost(costDTO, session);

        return "redirect:/view/table/costTable";
    }

    @GetMapping("view/table/cost/remove/{id}")
    public String removeCost(@PathVariable Long id) {
        costService.removeCostById(id);

        return "redirect:/view/table/costTable";
    }

    @GetMapping("view/table/cost/edit/{id}")
    public ModelAndView getCostDetail(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {

        var costDTO = costService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<Vendor> allVendor = vendorService.findAllVendor();
        modelAndView.addObject("costDTO", costDTO);
        modelAndView.addObject("allVendor", allVendor);
        return super.view("/view/edit/costEdit", "costDTO", costDTO, "allVendor",allVendor);
    }

    @PostMapping("view/table/cost/edit/{id}/edit")
    public String editCost(@PathVariable("id") Long id, CostEditDTO costEditDTO, HttpSession session) throws ObjectNotFoundException, IOException {

        costService.editCost(
                costEditDTO.getVendor().getId(),
                costEditDTO.getDescription(),
                costEditDTO.getAmount(),
                costEditDTO.getDateCost(),
                session,
                id);

        return "redirect:/view/table/costTable";
    }
}
