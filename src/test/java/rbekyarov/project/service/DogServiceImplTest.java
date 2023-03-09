package rbekyarov.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.multipart.MultipartFile;
import rbekyarov.project.models.dto.DogDTO;
import rbekyarov.project.models.dto.restDto.DogRestDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Breed;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.repository.DogRepository;
import rbekyarov.project.service.impl.DogServiceImpl;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DogServiceImplTest {

    @Mock
    private DogRepository dogRepository;

    @Mock
    private BehaviorService behaviorService;
    @Mock
    private MultipartFile file ;

    @Mock
    private ClientService clientService;

    @Mock
    private UserService userService;

    @Mock
    private BreedService breedService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DogServiceImpl dogService;

    private MockHttpSession session;
    private List<Dog> dogs;
    private Dog dog1;
    private DogDTO dogDTO;
    @Captor
    private ArgumentCaptor<Dog> dogCaptor;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        session = new MockHttpSession();
        dogs = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.setName("Buddy");
        dogs.add(dog1);
        Dog dog2 = new Dog();
        dog2.setName("Charlie");
        dogs.add(dog2);
        //file = mock(MultipartFile.class);

    }
    @Test
    public void testGetWeightById() {
        Long id = 1L;
        Integer weight = 10;
        when(dogRepository.getWeightById(id)).thenReturn(weight);

        Integer result = dogService.getWeightById(id);

        assertThat(result).isEqualTo(weight);
        verify(dogRepository).getWeightById(id);
    }
    @Test
    public void testFindAllDogByDesc() {

        when(dogRepository.findAllDogByDesc()).thenReturn(dogs);

        List<Dog> result = dogService.findAllDogByDesc();
        verify(dogRepository, times(1)).findAllDogByDesc();
        assertEquals(2, result.size());
        assertEquals("Buddy", result.get(0).getName());
        assertEquals("Charlie", result.get(1).getName());
    }



    @Test
    public void testRemoveDogById() {
        Long id = 1L;
        dogService.removeDogById(id);
        verify(dogRepository, times(1)).deleteById(id);
    }
    @Test
    public void testFindPaginated() {
        List<Dog> dogList = new ArrayList<>();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        dogList.add(dog1);
        dogList.add(dog2);
        when(dogRepository.findAllDogByDesc()).thenReturn(dogList);

        Page<Dog> dogsPage = dogService.findPaginated(PageRequest.of(0, 10));

        assertThat(dogsPage).isNotNull();

        verify(dogRepository).findAllDogByDesc();
    }
    @Test
    public void testFindById() {
        Long id = 1L;
        Dog dog = new Dog();
        dog.setName("Buddy");
        when(dogRepository.findById(id)).thenReturn(Optional.of(dog));

        Optional<Dog> result = dogService.findById(id);
        verify(dogRepository, times(1)).findById(id);
        assertTrue(result.isPresent());
        assertEquals("Buddy", result.get().getName());
    }
    @Test
    void findAll() {
        // given
        List<Dog> dogs = Arrays.asList(new Dog(), new Dog(), new Dog());
        when(dogRepository.findAll()).thenReturn(dogs);

        // when
        List<Dog> actualDogs = dogService.findAll();

        // then
        assertEquals(dogs.size(), actualDogs.size());
        verify(dogRepository).findAll();
    }
    @Test
    void getAllBehaviors() {
        // given
        List<Behavior> behaviors = Arrays.asList(new Behavior(), new Behavior(), new Behavior());
        when(behaviorService.findAllBehavior()).thenReturn(behaviors);

        // when
        List<Behavior> actualBehaviors = dogService.getAllBehaviors();

        // then
        assertEquals(behaviors.size(), actualBehaviors.size());
        verify(behaviorService).findAllBehavior();
    }
    @Test
    void getAllBreeds() {
        // given
        List<Breed> breeds = Arrays.asList(new Breed(), new Breed(), new Breed());
        when(breedService.findAllBreedById()).thenReturn(breeds);

        // when
        List<Breed> actualBreeds = dogService.getAllBreeds();

        // then
        assertEquals(breeds.size(), actualBreeds.size());
        verify(breedService).findAllBreedById();
    }
    @Test
    void getAllClients() {
        // given
        List<Client> clients = Arrays.asList(new Client(), new Client(), new Client());
        when(clientService.findAllClientByDesc()).thenReturn(clients);

        // when
        List<Client> actualClients = dogService.getAllClients();

        // then
        assertEquals(clients.size(), actualClients.size());
        verify(clientService).findAllClientByDesc();
    }
    @Test
    void listDogIdsTest() {
        Set<Long> dogIds = new HashSet<>();
        dogIds.add(1L);
        dogIds.add(2L);
        when(dogRepository.listDogIds("test@test.com")).thenReturn(dogIds);

        Set<Long> result = dogService.listDogIds("test@test.com");

        verify(dogRepository).listDogIds("test@test.com");
        assertSame(dogIds, result);
    }
    @Test
    void listDogByNameTest() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        dogs.add(new Dog());
        when(dogRepository.listDogByName("Fido")).thenReturn(dogs);

        List<Dog> result = dogService.listDogByName("Fido");

        verify(dogRepository).listDogByName("Fido");
        assertSame(dogs, result);
    }
    @Test
    void findAllDogByClientTest() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        dogs.add(new Dog());
        when(dogRepository.findAllDogByClient(1L)).thenReturn(dogs);

        List<Dog> result = dogService.findAllDogByClient(1L);

        verify(dogRepository).findAllDogByClient(1L);
        assertSame(dogs, result);
    }
    @Test
    void listDogByClientEmailTest() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        dogs.add(new Dog());
        when(dogRepository.listDogByClientEmail("test@test.com")).thenReturn(dogs);

        List<Dog> result = dogService.listDogByClientEmail("test@test.com");

        verify(dogRepository).listDogByClientEmail("test@test.com");
        assertSame(dogs, result);
    }
    @Test
    public void testFormatterLocalDate() {
        String dateDTO = "1.01.23 г.";
        LocalDate date = dogService.formatterLocalDate(dateDTO);
        assertEquals("2023-01-01",date.toString());

    }
    @Test
    void testFindAllDogForRest() {
        Dog dog = new Dog();
        dog.setId(1L);
        dog.setName("Dog1");
        dog.setYears("yrs.2 mos.2");
        dog.setLastDewormingDate(LocalDate.now());
        dog.setBirthDate(LocalDate.now());
        dog.setClient(new Client());
        dog.setBehavior(new Behavior());
        dog.setBreed(new Breed());


        when(dogRepository.findAll()).thenReturn(Arrays.asList(dog));
        when(modelMapper.map(dog, DogRestDTO.class)).thenReturn(new DogRestDTO());

        List<DogRestDTO> dogs = dogService.findAllDogForRest();

        assertEquals(1, dogs.size());
    }
//    @Test
//    void testImageUpload() throws IOException {
//        MultipartFile file = mock(MultipartFile.class);
//
//        when(file.isEmpty()).thenReturn(false);
//        when(file.getOriginalFilename()).thenReturn("image.png");
//
//        String imageName = dogService.imageUpload(file, "old_image.png");
//
//        assertEquals("image.png", imageName);
//    }
//
//    @Test
//    public void testEditDog() throws IOException {
//        // Arrange
//        Long id = 1L;
//        String imgName = "testImage.jpg";
//        MultipartFile file = mock(MultipartFile.class);
//        HttpSession session = mock(HttpSession.class);
//
//        DogEditDTO dogEditDTO = new DogEditDTO();
//        dogEditDTO.setName("Test Dog");
//        dogEditDTO.setBirthDate("2010-01-01");
//        dogEditDTO.setWeight(20);
//        dogEditDTO.setBreed(new Breed());
//        dogEditDTO.setLastDewormingDate("2020-10-10");
//        dogEditDTO.setSex(Sex.M);
//        dogEditDTO.setPassport(Passport.NO);
//        dogEditDTO.setMicrochip(Microchip.NO);
//        dogEditDTO.setClient(new Client());
//        dogEditDTO.setBehavior(new Behavior());
//        dogEditDTO.setMicrochipNumber("123456");
//
//
//        // Act
//        dogService.editDog(id, dogEditDTO, imgName, file, session);
//
//        // Assert
//        verify(dogRepository, times(1)).editDog(eq(dogEditDTO.getName()), any(LocalDate.class), eq(dogEditDTO.getWeight()), anyLong(), eq(dogEditDTO.getSex()), eq(dogEditDTO.getPassport()), eq(dogEditDTO.getMicrochip()), anyLong(), anyLong(), eq(imgName), eq(id), anyLong(), any(LocalDate.class), any(DogSize.class), anyString(), any(LocalDate.class), eq(dogEditDTO.getMicrochipNumber()));
//    }
}
