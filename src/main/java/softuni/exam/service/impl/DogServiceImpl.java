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

    private final BreedService breedService;
    private final ModelMapper modelMapper;

    public DogServiceImpl(DogRepository dogRepository, BehaviorService behaviorService, ClientService clientService, BreedService breedService, ModelMapper modelMapper) {
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

        Dog dogNew = modelMapper.map(dogDTO, Dog.class);

        String date = dogDTO.getBirthDate();

        dogNew.setBirthDate(formatterLocal(date));


        dogRepository.saveAndFlush(dogNew);
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
    public void editDog(String name, String birthDate, Integer weight, Long breedId, Sex sex, Passport passport, Microchip microchip, Long clientId, Long behaviorId, String imageName, Long id) {

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
                id);
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
