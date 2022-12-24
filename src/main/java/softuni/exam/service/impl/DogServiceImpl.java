package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DogDTO;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.Microchip;
import softuni.exam.models.entity.enums.Passport;
import softuni.exam.models.entity.enums.Sex;
import softuni.exam.repository.DogRepository;
import softuni.exam.service.BehaviorService;
import softuni.exam.service.BreedService;
import softuni.exam.service.ClientService;
import softuni.exam.service.DogService;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;
    private final BehaviorService behaviorService;
    private final ClientService clientService;

    private final BreedService breedService;
    private final ModelMapper modelMapper;

    public DogServiceImpl(DogRepository dogRepository, BehaviorService behaviorService, ClientService clientService, BreedService breedService, ModelMapper modelMapper
                          ) {
        this.dogRepository = dogRepository;
        this.behaviorService = behaviorService;
        this.clientService = clientService;
        this.breedService = breedService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Dog> findAllDogById() {
        return dogRepository.findAllDogById();
    }

    @Override
    public void addDog(DogDTO dogDTO) {
        Dog dog = modelMapper.map(dogDTO, Dog.class);

        String date = dogDTO.getBirthDate() ;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
        dog.setBirthDate(localDate);


        dogRepository.saveAndFlush(dog);
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
    public void editDog(String name, LocalDate birthDate, Integer weight, Long breedId, Sex sex, Passport passport, Microchip microchip, Long clientId, Long behaviorId, Long id) {
        dogRepository.editDog(name,
                birthDate,
                weight,
                breedId,
                sex,
                passport,
                microchip,
                clientId,
                behaviorId,
                id);
    }
    @Override
    public List<Behavior> getAllBehaviors(){
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
    public Set<Dog> findAllDogByClient(Long id) {
        return Set.copyOf(dogRepository.findAllDogByClient(id));
    }

}
