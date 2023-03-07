package rbekyarov.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Status;
import rbekyarov.project.repository.CellRepository;
import rbekyarov.project.service.impl.CellServiceImpl;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CellServiceImplTest {
    //private CellServiceImpl cellService;
    private CellRepository cellRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    @Mock
    private HttpSession httpSession;
    @InjectMocks
    private CellServiceImpl cellService;
    @BeforeEach
    public void setUp() {
        cellRepository = mock(CellRepository.class);
        userService = mock(UserService.class);
        modelMapper = mock(ModelMapper.class);
        cellService = new CellServiceImpl(cellRepository, modelMapper, userService);
    }

    @Test
    public void findAllEmptyCellsForCurrentDog_whenSmallDog_thenReturnsSmallCells() {
        List<Cell> expected = Arrays.asList(new Cell(), new Cell(), new Cell());
        when(cellRepository.findAllEmptyCellsSmall()).thenReturn(expected);

        List<Cell> actual = cellService.findAllEmptyCellsForCurrentDog(10);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllEmptyCellsForCurrentDog_whenMediumDog_thenReturnsMediumCells() {
        List<Cell> expected = Arrays.asList(new Cell(), new Cell(), new Cell());
        when(cellRepository.findAllEmptyCellsMedium()).thenReturn(expected);

        List<Cell> actual = cellService.findAllEmptyCellsForCurrentDog(15);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllEmptyCellsForCurrentDog_whenLargeDog_thenReturnsLargeCells() {
        List<Cell> expected = Arrays.asList(new Cell(), new Cell(), new Cell());
        when(cellRepository.findAllEmptyCellsLarge()).thenReturn(expected);

        List<Cell> actual = cellService.findAllEmptyCellsForCurrentDog(25);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllEmptyCells_whenInvoked_returnsAllEmptyCells() {
        List<Cell> expected = Arrays.asList(new Cell(), new Cell(), new Cell());
        when(cellRepository.findAllEmptyCells()).thenReturn(expected);

        List<Cell> actual = cellService.findAllEmptyCells();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findById_whenCellExists_returnsCell() {
        Cell expected = new Cell();
        when(cellRepository.findById(1L)).thenReturn(Optional.of(expected));

        Optional<Cell> actual = cellService.findById(1L);

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).isEqualTo(expected);
    }

    @Test
    public void testFindAllEmptyCells() {
        List<Cell> expected = new ArrayList<>();
        Cell cell1 = new Cell();
        cell1.setStatus(Status.empty);
        Cell cell2 = new Cell();
        cell2.setStatus(Status.empty);
        expected.add(cell1);
        expected.add(cell2);
        when(cellRepository.findAllEmptyCells()).thenReturn(expected);

        List<Cell> actual = cellService.findAllEmptyCells();

        assertEquals(expected, actual);
    }

    @Test
    public void testFindPaginated() {
        List<Cell> expected = new ArrayList<>();
        Cell cell1 = new Cell();
        cell1.setId(1L);
        cell1.setCode("Cell 1");
        Cell cell2 = new Cell();
        cell2.setId(2L);
        cell2.setCode("Cell 2");
        expected.add(cell1);
        expected.add(cell2);
        Page<Cell> page = new PageImpl<Cell>(expected, PageRequest.of(0, 10), expected.size());
        when(cellRepository.findAll()).thenReturn(expected);

        Page<Cell> actual = cellService.findPaginated(PageRequest.of(0, 10));

        assertEquals(page, actual);
    }
//    @Test
//    public void testAddCell() {
//        CellDTO cellDTO = new CellDTO();
//        cellDTO.setCode("Test Cell");
//        cellDTO.setStatus(Status.empty);
//
//        User user = new User();
//        user.setId(1L);
//
//        when(userService.getAuthorFromSession(httpSession)).thenReturn(user);
//
//        cellService.addCell(cellDTO, httpSession);
//
//        verify(cellRepository, times(1)).save(any(Cell.class));
//    }

    @Test
    public void testRemoveCellById() {
        Long cellId = 1L;

        cellService.removeCellById(cellId);

        verify(cellRepository, times(1)).deleteById(cellId);
    }

    @Test
    public void testFindById() {
        Long cellId = 1L;

        cellService.findById(cellId);

        verify(cellRepository, times(1)).findById(cellId);
    }

    @Test
    public void testEditCells() {
        Long cellId = 1L;
        String cellName = "Test Cell";
        Status cellStatus = Status.empty;

        User user = new User();
        user.setId(1L);

        when(userService.getAuthorFromSession(httpSession)).thenReturn(user);

        cellService.editCells(cellName, cellId, cellStatus, null, httpSession);

        verify(cellRepository, times(1)).editCell(cellName, cellId, null, cellStatus, user.getId(), LocalDate.now());
    }
}
