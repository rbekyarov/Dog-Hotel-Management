package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.dto.CellDTO;
import rbekyarov.project.models.dto.CellEditDTO;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.service.CellService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CellController extends BaseController {
    private final CellService cellService;

    public CellController(CellService cellService) {
        this.cellService = cellService;
    }

    @GetMapping("/view/table/cellTable")
    public ModelAndView cellTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Cell> cells = cellService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        int totalPages = cells.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("cells", cells);
        return super.view("/view/table/cellTable", "cells", cells,"pageNumbers", pageNumbers);
    }

    @GetMapping("/view/add/cellAdd")
    public ModelAndView cellAdd(ModelAndView modelAndView) {
        CellDTO cellDTO = new CellDTO();

        modelAndView.addObject("cellDTO", cellDTO);


        return super.view("/view/add/cellAdd", "cellDTO", cellDTO);

    }

    @PostMapping("/view/add/cellAdd")
    public String addCell(@Valid CellDTO cellDTO,HttpSession session) {

        cellService.addCells(cellDTO,session);

        return "redirect:/view/table/cellTable";
    }

    @GetMapping("view/table/cell/remove/{id}")
    public String removeCell(@PathVariable Long id) {
        cellService.removeCellById(id);

        return "redirect:/view/table/cellTable";
    }


    @GetMapping("view/table/cell/edit/{id}")
    public ModelAndView getCellDetail(@PathVariable("id") Long id,
                                      ModelAndView modelAndView) throws ObjectNotFoundException {

        var cellDto =
                cellService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("cellDto", cellDto);

        return super.view("/view/edit/cellEdit", "cellDto", cellDto);

    }

    @PostMapping("view/table/cell/edit/{id}/edit")
    public String editCell(@PathVariable("id") Long id, CellEditDTO cellEditDTO, HttpSession session) throws ObjectNotFoundException {
        var cellDto =
                cellService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        cellService.editCells(cellEditDTO.getCode(), id, cellEditDTO.getStatus(),session);

        return "redirect:/view/table/cellTable";
    }
}
