package softuni.exam.service;


import softuni.exam.models.dto.DogDTO;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.Microchip;
import softuni.exam.models.entity.enums.Passport;
import softuni.exam.models.entity.enums.Sex;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DogService {
    List<Dog> findAllDogById();



    void addDog(DogDTO dogDTO, HttpSession session);

    void removeDogById(Long id);

    Optional<Dog> findById(Long id);

    void editDog(String name,
                 String birthDate,
                 Integer weight,
                 Long breedId,
                 Sex sex,
                 Passport passport,
                 Microchip microchip,
                 Long clientId,
                 Long behaviorId,
                 String imageName,
                 Long id
            , HttpSession session);

    public List<Behavior> getAllBehaviors();

    public List<Breed> getAllBreeds();

    public List<Client> getAllClients();
    public Set<Long> listDogIds(String email);

    List<Dog> listDogByEmail(String email);
    List<Dog> findAllDogByClient(Long id);
}
