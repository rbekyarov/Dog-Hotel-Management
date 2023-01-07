package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.CellDTO;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Status;
import rbekyarov.project.repository.CellRepository;
import rbekyarov.project.service.CellService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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
    public void addCells(CellDTO cellDTO, HttpSession session) {
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
    public void editCells(String name, Long id, Status status,HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        cellRepository.editCell(name, id, status,editAuthorId,dateEdit);
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
}
