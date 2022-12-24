package softuni.exam.service;

import softuni.exam.models.dto.BreedDTO;
import softuni.exam.models.dto.DogDTO;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.Microchip;
import softuni.exam.models.entity.enums.Passport;
import softuni.exam.models.entity.enums.Sex;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DogService {
    List<Dog> findAllDogById();



    void addDog(DogDTO dogDTO);

    void removeDogById(Long id);

    Optional<Dog> findById(Long id);

    void editDog(String name,
                 LocalDate birthDate,
                 Integer weight,
                 Long breedId,
                 Sex sex,
                 Passport passport,
                 Microchip microchip,
                 Long clientId,
                 Long behaviorId,
                 Long id);

    public List<Behavior> getAllBehaviors();

    public List<Breed> getAllBreeds();

    public List<Client> getAllClients();
    public Set<Long> listDogIds(String email);

    List<Dog> listDogByEmail(String email);
    Set<Dog> findAllDogByClient(Long id);
}
