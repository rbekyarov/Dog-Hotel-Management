package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rbekyarov.project.models.dto.CostDTO;
import rbekyarov.project.models.dto.CostEditDTO;
import rbekyarov.project.models.entity.Cost;
import rbekyarov.project.models.entity.Vendor;
import rbekyarov.project.repository.CostRepository;
import rbekyarov.project.service.CostService;
import rbekyarov.project.service.VendorService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CostController extends BaseController {
    private final CostService costService;
    private final VendorService vendorService;
    private final CostRepository costRepository;
    private final HttpSession session;

    public CostController(CostService costService, VendorService vendorService,
                          CostRepository costRepository, HttpSession session) {
        this.costService = costService;
        this.vendorService = vendorService;
        this.costRepository = costRepository;
        this.session = session;
    }


    @GetMapping("/view/table/costTable")
    public ModelAndView costTable(ModelAndView modelAndView , @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
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
        BigDecimal totalAmountCost = costService.getTotalAmountCost();
        modelAndView.addObject("costs", costs);
        modelAndView.addObject("totalAmountCost", totalAmountCost);
        return super.view("/view/table/costTable", "costs", costs,"pageNumbers", pageNumbers,"totalAmountCost", totalAmountCost);
    }

    @GetMapping("/view/add/costAdd")
    public ModelAndView costAdd(ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        CostDTO costDTO = new CostDTO();
        List<Vendor> allVendor = vendorService.findAllVendor();
        modelAndView.addObject("costDTO", costDTO);
        modelAndView.addObject("allVendor", allVendor);

        return super.view("/view/add/costAdd", "costDTO", costDTO, "allVendor", allVendor);
    }

    @PostMapping("/view/add/costAdd")
    public ModelAndView addCost(@Valid CostDTO costDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) throws IOException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("costDTO", costDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.costDTO", bindingResult);
            List<Vendor> allVendor = vendorService.findAllVendor();
            return super.view("/view/add/costAdd", "costDTO", costDTO, "allVendor", allVendor);
        }
        costService.addCost(costDTO, session);
        return super.redirect("/view/table/costTable");

    }

    @GetMapping("view/table/cost/remove/{id}")
    public String removeCost(@PathVariable Long id) {
        if(checkValidSession()) {
            return "redirect:/view/login";
        }
        costService.removeCostById(id);

        return "redirect:/view/table/costTable";
    }

    @GetMapping("/view/table/cost/edit/{id}")
    public ModelAndView getCostDetail(@PathVariable("id") Long id, ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Cost costEditDTO = costService.findById(id).orElseThrow(() -> new ObjectNotFoundException("not found!"));
        List<Vendor> allVendor = vendorService.findAllVendor();
        modelAndView.addObject("costEditDTO", costEditDTO);
        modelAndView.addObject("allVendor", allVendor);
        return super.view("/view/edit/costEdit", "costEditDTO", costEditDTO, "allVendor",allVendor);
    }

    @PostMapping("/view/table/cost/edit/{id}")
    public ModelAndView editCost(@PathVariable("id") Long id, @Valid CostEditDTO costEditDTO,
                           BindingResult bindingResult,
                           HttpSession session, ModelAndView modelAndView) throws ObjectNotFoundException, IOException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            List<Vendor> allVendor = vendorService.findAllVendor();
            modelAndView.addObject("costEditDTO", costEditDTO);
            return super.view("/view/edit/costEdit", "costEditDTO", costEditDTO, "allVendor",allVendor);

        }
        costService.editCost(
                costEditDTO.getVendor().getId(),
                costEditDTO.getDescription(),
                costEditDTO.getInvoiceNo(),
                costEditDTO.getAmount(),
                costEditDTO.getDateCost(),
                session,
                id);
        return super.redirect("/view/table/costTable");
    }
    @RequestMapping(path = {"/","/view/table/searchCostByVendorName"})
    public ModelAndView search(ModelAndView modelAndView,@RequestParam("vendorName") String vendorName) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        List<Cost> costs = new ArrayList<>();
        if(!vendorName.equals("")) {
            costs = costService.findCostByVendor(vendorName);
            modelAndView.addObject("costs", costs);
        }else {
            costs = costService.findAllCostByDesc();
            modelAndView.addObject("costs", costs);}
        return super.view("/view/table/costTable", "costs", costs);
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
