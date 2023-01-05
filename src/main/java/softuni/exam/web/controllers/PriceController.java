package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.PriceDTO;
import softuni.exam.models.dto.PriceEditDTO;
import softuni.exam.models.entity.Price;
import softuni.exam.service.PriceService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PriceController extends BaseController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/view/table/priceTable")
    public ModelAndView priceTable(ModelAndView modelAndView) {

        List<Price> prices = priceService.findAllPriceById();
        modelAndView.addObject("prices", prices);
        return super.view("/view/table/priceTable", "prices", prices);
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
    public String addPrice(@Valid PriceDTO priceDTO) {

        priceService.addPrice(priceDTO);

        return "redirect:/view/table/priceTable";
    }

    @GetMapping("view/table/price/remove/{id}")
    public String removePrice(@PathVariable Long id) {
        priceService.removePriceById(id);

        return "redirect:/view/table/priceTable";
    }


    @GetMapping("view/table/price/edit/{id}")
    public ModelAndView getPriceDetail(@PathVariable("id") Long id,
                                       ModelAndView modelAndView) throws ObjectNotFoundException {

        var priceDTO =
                priceService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("priceDTO", priceDTO);

        return super.view("/view/edit/priceEdit", "priceDTO", priceDTO);

    }

    @PostMapping("view/table/price/edit/{id}/edit")
    public String editPrice(@PathVariable("id") Long id, PriceEditDTO priceEditDTO) throws ObjectNotFoundException {
        var priceDTO =
                priceService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        priceService.editPrice(priceEditDTO.getPriceOvernightStay(),
                priceEditDTO.getPriceFood(),
                priceEditDTO.getPriceTraining(),
                priceEditDTO.getPriceBathing(),
                priceEditDTO.getPriceCombing(),
                priceEditDTO.getPricePaws(),
                priceEditDTO.getPriceEars(),
                priceEditDTO.getPriceNails(),
                id);

        return "redirect:/view/table/priceTable";
    }
}
