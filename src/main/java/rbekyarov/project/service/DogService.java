package rbekyarov.project.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import rbekyarov.project.models.dto.DogDTO;
import rbekyarov.project.models.dto.DogEditDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Breed;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.Dog;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DogService {
    List<Dog> findAllDogByDesc();

    List<Dog> findAll();

    void addDog(DogDTO dogDTO, MultipartFile file,String imgName, HttpSession session) throws IOException;

    void removeDogById(Long id);

    Optional<Dog> findById(Long id);

    void editDog(Long id, DogEditDTO dogEditDTO, String imgName, MultipartFile file, HttpSession session) throws IOException;

    public List<Behavior> getAllBehaviors();

    public List<Breed> getAllBreeds();

    public List<Client> getAllClients();
    public Set<Long> listDogIds(String email);

    List<Dog> listDogByName(String name);
    List<Dog> findAllDogByClient(Long id);

    List<Dog> listDogByClientEmail(String clientEmail);
    Page<Dog> findPaginated(Pageable pageable);

    Integer getWeightById(Long id);

    void updateDogYears();


}
