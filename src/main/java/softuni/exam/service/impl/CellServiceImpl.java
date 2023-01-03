package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CellDTO;
import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.enums.Status;
import softuni.exam.repository.CellRepository;
import softuni.exam.service.CellService;

import java.util.List;
import java.util.Optional;

@Service
public class CellServiceImpl implements CellService {
    private final CellRepository cellRepository;
    private final ModelMapper modelMapper;

    public CellServiceImpl(CellRepository cellRepository, ModelMapper modelMapper) {
        this.cellRepository = cellRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Cell> findAllCellById() {
        return cellRepository.findAllCellById();
    }

    @Override
    public void addCells(CellDTO cellDTO) {
        Cell cell = modelMapper.map(cellDTO, Cell.class);
        cell.setStatus(cellDTO.getStatus());
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
    public void editCells(String name, Long id, Status status) {
        cellRepository.editCell(name, id, status);
    }

    @Override
    public List<Cell> findAllEmptyCells() {
        return cellRepository.findAllEmptyCells();
    }

    @Override
    public void setCellBusy(Long id) {
        cellRepository.setCellBusy(id);
    }
}
