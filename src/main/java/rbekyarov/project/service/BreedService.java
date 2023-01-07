package rbekyarov.project.service;

import rbekyarov.project.models.dto.BreedDTO;
import rbekyarov.project.models.entity.Breed;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface BreedService {
    List<Breed> findAllBreedById();

    void addBreeds(BreedDTO breedDTO, HttpSession session);

    void removeBreedById(Long id);

    Optional<Breed> findById(Long id);

    void editBreeds(String name, Long id, HttpSession session);

}
