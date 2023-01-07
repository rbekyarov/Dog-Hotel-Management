package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.dto.CellDTO;
import rbekyarov.project.models.dto.CellEditDTO;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.service.CellService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CellController extends BaseController {
    private final CellService cellService;

    public CellController(CellService cellService) {
        this.cellService = cellService;
    }

    @GetMapping("/view/table/cellTable")
    public ModelAndView cellTable(ModelAndView modelAndView) {

        List<Cell> cells = cellService.findAllCellById();
        modelAndView.addObject("cells", cells);
        return super.view("/view/table/cellTable", "cells", cells);
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
