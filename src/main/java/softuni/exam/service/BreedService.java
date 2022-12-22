package softuni.exam.service;

import softuni.exam.models.dto.BehaviorDTO;
import softuni.exam.models.dto.BreedDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Breed;

import java.util.List;
import java.util.Optional;

public interface BreedService {
    List<Breed> findAllBreedById();

    void addBreeds(BreedDTO breedDTO);

    void removeBreedById(Long id);

    Optional<Breed> findById(Long id);

    void editBreeds(String name, Long id);

}
