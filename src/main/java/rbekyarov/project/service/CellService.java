package rbekyarov.project.service;

import rbekyarov.project.models.dto.CellDTO;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.enums.Status;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface CellService {
    List<Cell> findAllCellById();

    void addCells(CellDTO cellDTO, HttpSession session);

    void removeCellById(Long id);

    Optional<Cell> findById(Long id);

    void editCells(String name, Long id , Status status, HttpSession session);

    List<Cell> findAllEmptyCells();

    void setCellBusy(Long id);
    void setCellEmpty(Long id);
}