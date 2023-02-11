package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rbekyarov.project.models.dto.PriceDTO;
import rbekyarov.project.models.dto.PriceEditDTO;
import rbekyarov.project.models.entity.Price;
import rbekyarov.project.service.PriceService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PriceController extends BaseController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/view/table/priceTable")
    public ModelAndView priceTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Price> prices = priceService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = prices.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("prices", prices);
        return super.view("/view/table/priceTable", "prices", prices,"pageNumbers", pageNumbers);
    }
    @GetMapping("/view/table/priceTableUser")
    public ModelAndView priceTableUser(ModelAndView modelAndView) {

        List<Price> prices = priceService.findAllPriceById();
        modelAndView.addObject("prices", prices);
        return super.view("/view/table/priceTableUser", "prices", prices);
    }

    @GetMapping("/view/add/priceAdd")
    public ModelAndView priceAdd(ModelAndView modelAndView) {
        PriceDTO priceDTO = new PriceDTO();

        modelAndView.addObject("priceDTO", priceDTO);


        return super.view("/view/add/priceAdd", "priceDTO", priceDTO);

    }

    @PostMapping("/view/add/priceAdd")
    public ModelAndView addPrice(@Valid PriceDTO priceDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("priceDTO", priceDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.priceDTO", bindingResult);

            return super.view("/view/add/priceAdd");
        }
        priceService.addPrice(priceDTO);
        return super.redirect("/view/table/priceTable");

    }

    @GetMapping("view/table/price/remove/{id}")
    public String removePrice(@PathVariable Long id) {
        priceService.removePriceById(id);

        return "redirect:/view/table/priceTable";
    }


    @GetMapping("/view/table/price/edit/{id}")
    public ModelAndView getPriceDetail(@PathVariable("id") Long id,
                                       ModelAndView modelAndView) throws ObjectNotFoundException {

        Price priceEditDTO =
                priceService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("priceEditDTO", priceEditDTO);

        return super.view("/view/edit/priceEdit", "priceEditDTO", priceEditDTO);

    }

    @PostMapping("/view/table/price/edit/{id}")
    public ModelAndView editPrice(@PathVariable("id") Long id, @Valid PriceEditDTO priceEditDTO,
                            BindingResult bindingResult,
                            ModelAndView modelAndView) throws ObjectNotFoundException {
        if (bindingResult.hasErrors()) {

            modelAndView.addObject("priceEditDTO", priceEditDTO);
            return super.view("view/edit/priceEdit","priceEditDTO", priceEditDTO);

        }
        priceService.editPrice(
                priceEditDTO.getPriceStayS(),
                priceEditDTO.getPriceStayM(),
                priceEditDTO.getPriceStayL(),
                priceEditDTO.getPriceFood(),
                priceEditDTO.getPriceDeworming(),
                priceEditDTO.getPriceTraining(),
                priceEditDTO.getPriceBathing(),
                priceEditDTO.getPriceCombing(),
                priceEditDTO.getPricePaws(),
                priceEditDTO.getPriceEars(),
                priceEditDTO.getPriceNails(),
                priceEditDTO.getDiscountClientRegular(),
                priceEditDTO.getDiscountClientVip(),
                id);

        return super.redirect("/view/table/priceTable");

    }
}
