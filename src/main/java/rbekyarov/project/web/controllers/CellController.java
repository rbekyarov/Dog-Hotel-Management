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
import rbekyarov.project.models.dto.CellDTO;
import rbekyarov.project.models.dto.CellEditDTO;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.repository.ReservationRepository;
import rbekyarov.project.service.CellService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CellController extends BaseController {
    private final CellService cellService;
    private final ReservationRepository reservationRepository;
private final HttpSession session;
    public CellController(CellService cellService, ReservationRepository reservationRepository, HttpSession session) {
        this.cellService = cellService;
        this.reservationRepository = reservationRepository;
        this.session = session;
    }

    @GetMapping("/view/table/cellTable")
    public ModelAndView cellTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
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
        List<Reservation> allActiveReservation = reservationRepository.findAllActiveReservation();
        List<Cell> cellsRepair = cellService.findAllRepairsCells();
        cellService.updateStatus(reservationRepository.findAllActiveReservation());
        modelAndView.addObject("cells", cells);
        modelAndView.addObject("allActiveReservation", allActiveReservation);
        modelAndView.addObject("cellsRepair", cellsRepair);
        return super.view("/view/table/cellTable", "cells",  cells,"pageNumbers", pageNumbers,"cellsRepair", cellsRepair,"allActiveReservation", allActiveReservation);
    }

    @GetMapping("/view/add/cellAdd")
    public ModelAndView cellAdd(ModelAndView modelAndView) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        CellDTO cellDTO = new CellDTO();

        modelAndView.addObject("cellDTO", cellDTO);


        return super.view("/view/add/cellAdd", "cellDTO", cellDTO);

    }

    @PostMapping("/view/add/cellAdd")
    public ModelAndView addCell(@Valid CellDTO cellDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("cellDTO", cellDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.cellDTO", bindingResult);

            return super.view("/view/add/cellAdd");
        }
        cellService.addCell(cellDTO,session);
        return super.redirect("/view/table/cellTable");

    }

    @GetMapping("view/table/cell/remove/{id}")
    public ModelAndView removeBehavior(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        boolean isUsed = false;
        List<Cell> cells = reservationRepository.listUsedCell();
        for (Cell c : cells) {
            if(Objects.equals(c.getId(), id)){
                redirectAttributes.addFlashAttribute("isUsed", true);
                isUsed =true;
            }
        }
        if(!isUsed){
            cellService.removeCellById(id);
        }
        return super.redirect("/view/table/cellTable");
    }


    @GetMapping("/view/table/cell/edit/{id}")
    public ModelAndView getCellDetail(@PathVariable("id") Long id,
                                      ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Cell cellEditDTO =
                cellService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("cellEditDTO", cellEditDTO);

        return super.view("/view/edit/cellEdit", "cellEditDTO", cellEditDTO);

    }

    @PostMapping("/view/table/cell/edit/{id}")
    public ModelAndView editCell(@PathVariable("id") Long id,
                           @Valid CellEditDTO cellEditDTO,
                           BindingResult bindingResult,RedirectAttributes redirectAttributes,
                           HttpSession session) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("cellEditDTO", cellEditDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.cellEditDTO", bindingResult);


           return super.view("view/edit/cellEdit","cellEditDTO", cellEditDTO);
        }


//        if (bindingResult.hasErrors()) {
//
//            modelAndView.addObject("cellEditDTO", cellEditDTO);
//            return super.view("view/edit/cellEdit","cellEditDTO", cellEditDTO);
//
//        }

        cellService.editCells(cellEditDTO.getCode(), id, cellEditDTO.getStatus(),cellEditDTO.getCellSize(),session);
        return super.redirect("/view/table/cellTable");

    }
    public boolean checkValidSession(){
        Object user = session.getAttribute("user");
        Object admin = session.getAttribute("admin");

        if(admin ==null && user==null){
            return   true;

        }
        return false;
    }







//    @GetMapping("view/table/cell/edit/{id}")
//    public ModelAndView getCellDetail(@PathVariable("id") Long id,
//                                      ModelAndView modelAndView) throws ObjectNotFoundException {
//
//        Cell cellDto =
//                cellService.findById(id).
//                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
//
//        modelAndView.addObject("cellDto", cellDto);
//
//        return super.view("/view/edit/cellEdit", "cellDto", cellDto);
//
//    }
//
//    @PostMapping("view/table/cell/edit/{id}/edit")
//    public String editCell(@PathVariable("id") Long id, CellEditDTO cellEditDTO, HttpSession session) throws ObjectNotFoundException {
//        cellService.editCells(cellEditDTO.getCode(), id, cellEditDTO.getStatus(),cellEditDTO.getCellSize(),session);
//
//        return "redirect:/view/table/cellTable";
//    }
}