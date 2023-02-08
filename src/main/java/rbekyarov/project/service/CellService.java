package rbekyarov.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.CellDTO;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.enums.CellSize;
import rbekyarov.project.models.entity.enums.Status;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface CellService {
    List<Cell> findAllCellById();

    void addCell(CellDTO cellDTO, HttpSession session);

    void removeCellById(Long id);

    Optional<Cell> findById(Long id);

    void editCells(String name, Long id, Status status, CellSize cellSize, HttpSession session);

    List<Cell> findAllEmptyCellsForCurrentDog(Integer weight);
    List<Cell> findAllEmptyCells();

    void setCellBusy(Long id);
    void setCellEmpty(Long id);
    Page<Cell> findPaginated(Pageable pageable);

    void updateStatus(List<Reservation> reservationList);

    List<Cell> findAllCellWithoutCellInService();

    List<Cell> findAllRepairsCells();
}

