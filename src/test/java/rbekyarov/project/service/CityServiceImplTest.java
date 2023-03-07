package rbekyarov.project.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.CityDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.CityRepository;
import rbekyarov.project.service.impl.CityServiceImpl;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private CityServiceImpl cityService;
    private City city1;
    private City city2;
    private City city3;
    @BeforeEach
    void setUp() {
        city1 = new City("1000", "Sofia", null, LocalDate.now());
        city2 = new City("2000", "Plovdiv", null, LocalDate.now());
        city3 = new City("3000", "Varna", null, LocalDate.now());
    }
    @Test
    public void findAllCityById() {
        // Arrange
        List<City> cities = Arrays.asList(
                new City( "1000", "Sofia", null, null),
                new City( "4000", "Plovdiv", null, null)
        );
        when(cityRepository.findAllCityById()).thenReturn(cities);

        // Act
        List<City> result = cityService.findAllCityById();

        // Assert
        assertThat(result).isEqualTo(cities);
        verify(cityRepository).findAllCityById();
    }

    @Test
    public void addCity() {
        // Arrange
        CityDTO cityDTO = new CityDTO();
        HttpSession session = mock(HttpSession.class);
        when(userService.getAuthorFromSession(session)).thenReturn(null);
        when(modelMapper.map(cityDTO, City.class)).thenReturn(new City());

        // Act
        cityService.addCity(cityDTO, session);

        // Assert
        verify(cityRepository).save(any(City.class));
        verify(userService).getAuthorFromSession(session);
        verify(modelMapper).map(cityDTO, City.class);
    }

    @Test
    public void removeCityById() {
        // Arrange
        Long id = 1L;

        // Act
        cityService.removeCityById(id);

        // Assert
        verify(cityRepository).deleteById(id);
    }

    @Test
    public void findById() {
        // Arrange
        Long id = 1L;
        City city = new City( "1000", "Sofia", null, null);
        when(cityRepository.findById(id)).thenReturn(Optional.of(city));

        // Act
        Optional<City> result = cityService.findById(id);

        // Assert
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(city);
        verify(cityRepository).findById(id);
    }

    @Test
    public void editCity() {
        // Arrange
        String code = "1000";
        Long id = 1L;
        String name = "Sofia";
        HttpSession session = mock(HttpSession.class);
        User author = new User();
        author.setId(1L);
        author.setUsername("username");
        when(userService.getAuthorFromSession(session)).thenReturn(author);

        // Act
        cityService.editCity(code, id, name, session);

        // Assert
        verify(cityRepository).editCity(code, id, name, author.getId(), LocalDate.now());
        verify(userService).getAuthorFromSession(session);
    }
    @Test
    public void testFindPaginated() {
        // Arrange
        int pageNumber = 1;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<City> allCities = new ArrayList<>();
        allCities.add(city1);
        allCities.add(city2);
        allCities.add(city3);
        Mockito.when(cityRepository.findAll()).thenReturn(allCities);

        // Act
        Page<City> citiesPage = cityService.findPaginated(pageable);

        // Assert
        assertThat(citiesPage).isNotNull();
        assertThat(citiesPage.getTotalElements()).isEqualTo(3);
        assertThat(citiesPage.getTotalPages()).isEqualTo(2);
        assertThat(citiesPage.getNumber()).isEqualTo(pageNumber);
        assertThat(citiesPage.getSize()).isEqualTo(pageSize);
    }

}




