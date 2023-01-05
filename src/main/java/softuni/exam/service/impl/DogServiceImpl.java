package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DogDTO;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.Microchip;
import softuni.exam.models.entity.enums.Passport;
import softuni.exam.models.entity.enums.Sex;
import softuni.exam.repository.DogRepository;
import softuni.exam.service.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;
    private final BehaviorService behaviorService;
    private final ClientService clientService;
    private final UserService userService;

    private final BreedService breedService;
    private final ModelMapper modelMapper;

    public DogServiceImpl(DogRepository dogRepository, BehaviorService behaviorService, ClientService clientService, UserService userService, BreedService breedService, ModelMapper modelMapper) {
        this.dogRepository = dogRepository;
        this.behaviorService = behaviorService;
        this.clientService = clientService;
        this.userService = userService;
        this.breedService = breedService;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<Dog> findAllDogById() {
        return dogRepository.findAllDogById();
    }

    @Override
    public void addDog(DogDTO dogDTO, HttpSession session) {

        Dog dogNew = modelMapper.map(dogDTO, Dog.class);

        String date = dogDTO.getBirthDate();

        dogNew.setBirthDate(formatterLocal(date));
         //get and set Author
        dogNew.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        dogNew.setDateCreate(LocalDate.now());

        dogRepository.save(dogNew);
    }

    @Override
    public void removeDogById(Long id) {
        dogRepository.deleteById(id);
    }

    @Override
    public Optional<Dog> findById(Long id) {
        return dogRepository.findById(id);
    }

    @Override
    public void editDog(String name, String birthDate, Integer weight, Long breedId, Sex sex, Passport passport, Microchip microchip, Long clientId, Long behaviorId, String imageName, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        dogRepository.editDog(name,
                formatterLocal(birthDate),
                weight,
                breedId,
                sex,
                passport,
                microchip,
                clientId,
                behaviorId,
                imageName,
                id,
                editAuthorId,
                dateEdit);
    }

    @Override
    public List<Behavior> getAllBehaviors() {
        return behaviorService.findAllBehaviorById();
    }

    @Override
    public List<Breed> getAllBreeds() {
        return breedService.findAllBreedById();
    }

    @Override
    public List<Client> getAllClients() {
        return clientService.findAllClientById();
    }

    @Override
    public Set<Long> listDogIds(String email) {
        return dogRepository.listDogIds(email);
    }

    @Override
    public List<Dog> listDogByEmail(String email) {
        return dogRepository.listDogByEmail(email);
    }

    @Override
    public List<Dog> findAllDogByClient(Long id) {
        return dogRepository.findAllDogByClient(id);
    }

    //convert String to LocalDate
    LocalDate formatterLocal(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return localDate;
    }
}
