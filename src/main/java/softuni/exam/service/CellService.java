package softuni.exam.service;

import softuni.exam.models.dto.CellDTO;
import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.enums.Status;

import java.util.List;
import java.util.Optional;

public interface CellService {
    List<Cell> findAllCellById();

    void addCells(CellDTO cellDTO);

    void removeCellById(Long id);

    Optional<Cell> findById(Long id);

    void editCells(String name, Long id , Status status);

    List<Cell> findAllEmptyCells();
}
