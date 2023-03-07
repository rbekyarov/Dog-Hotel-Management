package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.CellDTO;
import rbekyarov.project.models.dto.UserRegisterDTO;
import rbekyarov.project.models.dto.restDto.BehaviorRestDTO;
import rbekyarov.project.models.dto.restDto.CellRestDTO;
import rbekyarov.project.models.dto.restDto.UserRestDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.CellSize;
import rbekyarov.project.models.entity.enums.Status;
import rbekyarov.project.repository.CellRepository;
import rbekyarov.project.service.CellService;
import rbekyarov.project.service.ReservationService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CellServiceImpl implements CellService {
    private final CellRepository cellRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public CellServiceImpl(CellRepository cellRepository, ModelMapper modelMapper, UserService userService) {
        this.cellRepository = cellRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;

    }

    @Override
    public List<Cell> findAllCellById() {
        return cellRepository.findAllCellById();
    }

    @Override
    public void addCell(CellDTO cellDTO, HttpSession session) {
        Cell cell = modelMapper.map(cellDTO, Cell.class);
        cell.setStatus(cellDTO.getStatus());
        //get and set Author
        cell.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        cell.setDateCreate(LocalDate.now());

        cellRepository.save(cell);
    }

    @Override
    public void removeCellById(Long id) {
        cellRepository.deleteById(id);
    }

    @Override
    public Optional<Cell> findById(Long id) {
        return cellRepository.findById(id);
    }

    @Override
    public void editCells(String name, Long id, Status status,CellSize cellSize,HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        cellRepository.editCell(name, id,cellSize, status,editAuthorId,dateEdit);
    }

    @Override
    public List<Cell> findAllEmptyCellsForCurrentDog(Integer weight) {
        if (weight <= 10) {
            return cellRepository.findAllEmptyCellsSmall();
        } else if (weight <= 20) {
            return cellRepository.findAllEmptyCellsMedium();
        }else {
            return cellRepository.findAllEmptyCellsLarge();
        }

    }
    @Override
    public List<Cell> findAllEmptyCells() {
        return cellRepository.findAllEmptyCells();

    }

    @Override
    public void setCellBusy(Long id) {
        cellRepository.setCellBusy(id);
    }

    @Override
    public void setCellEmpty(Long id) {
        cellRepository.setCellEmpty(id);
    }

    @Override
    public Page<Cell> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Cell> list;
        List<Cell> cells = cellRepository.findAll();
        if (cells.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, cells.size());
            list = cells.subList(startItem, toIndex);
        }

        Page<Cell> cellsPage = new PageImpl<Cell>(list, PageRequest.of(currentPage, pageSize), cells.size());

        return cellsPage;
    }

    @Override
    public void updateStatus(List<Reservation> allActiveReservation) {

        for (Reservation reservation : allActiveReservation) {
            Cell cell = reservation.getCell();
            cellRepository.setCellBusy(cell.getId());
        }

    }

    @Override
    public List<Cell> findAllCellWithoutCellInService() {
        return cellRepository.findAllCellWithoutCellInService();
    }

    @Override
    public List<Cell> findAllRepairsCells() {
        return cellRepository.findAllRepairsCells();
    }

    @Override
    public List<CellRestDTO> findAllCellForRest() {
        return cellRepository.findAll().
                stream().
                map(this::map).
                toList();
    }

    private CellRestDTO map(Cell cell) {
             CellRestDTO cellRestDTO = new CellRestDTO();
             cellRestDTO.setId(cell.getId());
             cellRestDTO.setCellSize(cell.getCellSize());
             cellRestDTO.setId(cell.getId());
             cellRestDTO.setCode(cell.getCode());
             cellRestDTO.setStatus(cell.getStatus());
        return  cellRestDTO;
    }
}
