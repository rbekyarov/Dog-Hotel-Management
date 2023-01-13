package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.dto.CostDTO;
import rbekyarov.project.models.dto.CostEditDTO;
import rbekyarov.project.models.entity.Cost;
import rbekyarov.project.models.entity.Vendor;
import rbekyarov.project.service.CostService;
import rbekyarov.project.service.VendorService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CostController extends BaseController {
    private final CostService costService;
    private final VendorService vendorService;

    public CostController(CostService costService, VendorService vendorService) {
        this.costService = costService;
        this.vendorService = vendorService;
    }


    @GetMapping("/view/table/costTable")
    public ModelAndView costTable(ModelAndView modelAndView , @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Cost> costs = costService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = costs.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("costs", costs);
        return super.view("/view/table/costTable", "costs", costs,"pageNumbers", pageNumbers);
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

        Cost costDTO = costService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
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
                costEditDTO.getInvoiceNo(),
                costEditDTO.getAmount(),
                costEditDTO.getDateCost(),
                session,
                id);

        return "redirect:/view/table/costTable";
    }
    @RequestMapping(path = {"/","/view/table/searchCostByVendorName"})
    public ModelAndView search(ModelAndView modelAndView,@RequestParam("vendorName") String vendorName) {
        List<Cost> costs = new ArrayList<>();
        if(!vendorName.equals("")) {
            costs = costService.findCostByVendor(vendorName);
            modelAndView.addObject("costs", costs);
        }else {
            costs = costService.findAllCostByDesc();
            modelAndView.addObject("costs", costs);}
        return super.view("/view/table/costTable", "costs", costs);
    }
}
