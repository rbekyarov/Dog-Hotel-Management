package rbekyarov.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.BreedDTO;
import rbekyarov.project.models.dto.restDto.BreedRestDTO;
import rbekyarov.project.models.entity.Breed;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.BreedRepository;
import rbekyarov.project.service.UserService;
import rbekyarov.project.service.impl.BreedServiceImpl;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class BreedServiceImplTest {

    private BreedService breedService;

    @Mock
    private BreedRepository breedRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        breedService = new BreedServiceImpl(breedRepository, modelMapper, userService);
    }

    @Test
    public void testFindAllBreedById() {
        // arrange
        Breed breed = new Breed();
        List<Breed> breeds = new ArrayList<>();
        breeds.add(breed);
        when(breedRepository.findAllBreedById()).thenReturn(breeds);

        // act
        List<Breed> result = breedService.findAllBreedById();

        // assert
        assertEquals(breeds, result);
    }

    @Test
    public void testAddBreeds() {
        // arrange
        BreedDTO breedDTO = new BreedDTO();
        Breed breed = new Breed();
        when(modelMapper.map(breedDTO, Breed.class)).thenReturn(breed);
        when(userService.getAuthorFromSession(session)).thenReturn(new User());
        when(breedRepository.save(breed)).thenReturn(breed);

        // act
        breedService.addBreeds(breedDTO, session);

        // assert
        verify(breedRepository, times(1)).save(breed);
        assertEquals(LocalDate.now(), breed.getDateCreate());
    }

    @Test
    public void testRemoveBreedById() {
        // arrange
        Long id = 1L;

        // act
        breedService.removeBreedById(id);

        // assert
        verify(breedRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindById() {
        // arrange
        Long id = 1L;
        Breed breed = new Breed();
        when(breedRepository.findById(id)).thenReturn(Optional.of(breed));

        // act
        Optional<Breed> result = breedService.findById(id);

        // assert
        assertEquals(Optional.of(breed), result);
    }

    @Test
    public void testEditBreeds() {
        // arrange
        Long id = 1L;
        String name = "newName";
        User user = new User();
        user.setId(1L);
        when(userService.getAuthorFromSession(session)).thenReturn(user);

        // act
        breedService.editBreeds(name, id, session);

        // assert
        verify(breedRepository, times(1)).editBreed(eq(name), eq(id), eq(user.getId()), any(LocalDate.class));

    }
    @Test
    public void testFindPaginated() {
        List<Breed> breeds = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Breed breed = new Breed();
            breed.setId(Long.valueOf(i));
            breed.setBreedName("Breed " + i);
            breeds.add(breed);
        }
        Page<Breed> page = new PageImpl<>(breeds);
        when(breedRepository.findAll()).thenReturn(breeds);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Breed> result = breedService.findPaginated(pageable);
        Assertions.assertEquals(2, result.getTotalPages());
        Assertions.assertEquals(5, result.getContent().size());
    }

}