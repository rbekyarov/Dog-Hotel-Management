package rbekyarov.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import rbekyarov.project.models.dto.ClientDTO;
import rbekyarov.project.models.dto.DogDTO;
import rbekyarov.project.models.dto.ReservationDTO;
import rbekyarov.project.models.dto.restDto.ReservationRestDTO;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.enums.*;
import rbekyarov.project.repository.ClientRepository;
import rbekyarov.project.repository.DogRepository;
import rbekyarov.project.repository.ReservationRepository;
import rbekyarov.project.service.impl.ReservationServiceImpl;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private PriceService priceService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CellService cellService;

    @Mock
    private UserService userService;

    @Mock
    private CompanyService companyService;

    @Mock
    private DogRepository dogRepository;

    @Mock
    private DogService dogService;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllReservationByDesc() {
        Reservation r1 = new Reservation();
        Reservation r2 = new Reservation();
        List<Reservation> expectedReservations = Arrays.asList(r1, r2);
        when(reservationRepository.findAllReservationByDesc()).thenReturn(expectedReservations);
        List<Reservation> actualReservations = reservationService.findAllReservationByDesc();
        verify(reservationRepository, times(1)).findAllReservationByDesc();
        assertEquals(expectedReservations, actualReservations);
    }

    @Test
    void testListReservationById() {
        Reservation r1 = new Reservation();
        List<Reservation> expectedReservations = Arrays.asList(r1);
        when(reservationRepository.listReservationById(anyLong())).thenReturn(expectedReservations);
        List<Reservation> actualReservations = reservationService.listReservationById(1L);
        verify(reservationRepository, times(1)).listReservationById(anyLong());
        assertEquals(expectedReservations, actualReservations);
    }

    @Test
    void testFindAllActiveReservation() {
        Reservation r1 = new Reservation();
        Reservation r2 = new Reservation();
        List<Reservation> expectedReservations = Arrays.asList(r1, r2);
        when(reservationRepository.findAllActiveReservation()).thenReturn(expectedReservations);
        List<Reservation> actualReservations = reservationService.findAllActiveReservation();
        verify(reservationRepository, times(1)).findAllActiveReservation();
        assertEquals(expectedReservations, actualReservations);
    }

    @Test
    void testFindAllActiveReservationLimit3() {
        Reservation r1 = new Reservation();
        Reservation r2 = new Reservation();
        List<Reservation> expectedReservations = Arrays.asList(r1, r2);
        when(reservationRepository.findAllActiveReservationLimit3()).thenReturn(expectedReservations);
        List<Reservation> actualReservations = reservationService.findAllActiveReservationLimit3();
        verify(reservationRepository, times(1)).findAllActiveReservationLimit3();
        assertEquals(expectedReservations, actualReservations);
    }

    @Test
    public void testFindActiveReservedDogs() {
        // Arrange
        List<Dog> expected = Arrays.asList(new Dog(), new Dog());
        when(reservationRepository.findActiveReservedDogs()).thenReturn(expected);

        // Act
        List<Dog> actual = reservationService.findActiveReservedDogs();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllUpcomingReservations() {
        // Arrange
        List<Reservation> expected = Arrays.asList(new Reservation(), new Reservation());
        when(reservationRepository.findAllUpcomingReservations()).thenReturn(expected);

        // Act
        List<Reservation> actual = reservationService.findAllUpcomingReservations();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveReservationById() {
        // Arrange
        Long id = 1L;

        // Act
        reservationService.removeReservationById(id);

        // Assert
        verify(reservationRepository).deleteById(id);
    }

    @Test
    public void testFindById() {
        // Arrange
        Long id = 1L;
        Reservation expected = new Reservation();
        when(reservationRepository.findById(id)).thenReturn(Optional.of(expected));

        // Act
        Optional<Reservation> actual = reservationService.findById(id);

        // Assert
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void testSetCellEmptyByReservationID() {
        // Arrange
        Long id = 1L;
        Reservation reservation = new Reservation();
        reservation.setCell(new Cell());
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        // Act
        reservationService.setCellEmptyByReservationID(id);

        // Assert
        verify(cellService).setCellEmpty(reservation.getCell().getId());
    }

    @Test
    public void testChangeInvoicedStatus() {
        // Arrange
        Long id = 1L;
        Invoiced invoiced = Invoiced.YES;

        // Act
        reservationService.changeInvoicedStatus(id, invoiced);

        // Assert
        verify(reservationRepository).changeInvoiced(id, invoiced);
    }

    @Test
    public void testListReservationByClientEmail() {
        // Arrange
        String clientEmail = "test@test.com";
        List<Reservation> expected = Arrays.asList(new Reservation(), new Reservation());
        when(reservationRepository.listReservationByClientEmail(clientEmail)).thenReturn(expected);

        // Act
        List<Reservation> actual = reservationService.listReservationByClientEmail(clientEmail);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetCountActiveReservation() {
        when(reservationRepository.getCountActiveReservation()).thenReturn(2);
        int count = reservationService.getCountActiveReservation();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void shouldGetCountUpcomingReservation() {
        when(reservationRepository.getCountUpcomingReservation()).thenReturn(3);
        int count = reservationService.getCountUpcomingReservation();
        assertThat(count).isEqualTo(3);
    }

    @Test
    void shouldGetCountCompletedReservation() {
        when(reservationRepository.getCountCompletedReservation()).thenReturn(4);
        int count = reservationService.getCountCompletedReservation();
        assertThat(count).isEqualTo(4);
    }
    @Test
    void testCheckReservationIsInvoicedWithInvoicedReservation() {
        // given
        Reservation reservation = new Reservation();
        reservation.setInvoiced(Invoiced.YES);
        when(reservationRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(reservation));

        // when
        boolean result = reservationService.checkReservationIsInvoised(1L);

        // then
        assertTrue(result);
    }

    @Test
    void testCheckReservationIsInvoicedWithNonInvoicedReservation() {
        // given
        Reservation reservation = new Reservation();
        reservation.setInvoiced(Invoiced.NO);
        when(reservationRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(reservation));

        // when
        boolean result = reservationService.checkReservationIsInvoised(1L);

        // then
        assertFalse(result);
    }

    @Test
    public void testFindAllReservationForRest() {
        List<Reservation> reservationList = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now());
        reservation.setClient(new Client());
        reservation.setDog(new Dog());
        reservation.setCell(new Cell());
        reservation.setStatusReservation(StatusReservation.active);
        reservationList.add(reservation);

        when(reservationRepository.findAll()).thenReturn(reservationList);

        ReservationRestDTO reservationRestDTO = new ReservationRestDTO();
        reservationRestDTO.setId(reservation.getId());
        reservationRestDTO.setStatus(reservation.getStatusReservation());
        when(modelMapper.map(any(Reservation.class), any())).thenReturn(reservationRestDTO);

        List<ReservationRestDTO> result = reservationService.findAllReservationForRest();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(reservation.getId(), result.get(0).getId());
        Assertions.assertEquals(reservation.getStatusReservation(), result.get(0).getStatus());
    }

}
