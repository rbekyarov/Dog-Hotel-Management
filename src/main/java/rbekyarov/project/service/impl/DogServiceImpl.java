package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rbekyarov.project.models.dto.DogEditDTO;
import rbekyarov.project.models.entity.*;
import rbekyarov.project.models.entity.enums.Microchip;
import rbekyarov.project.models.entity.enums.Passport;
import rbekyarov.project.models.entity.enums.Sex;
import rbekyarov.project.models.dto.DogDTO;
import rbekyarov.project.service.*;
import rbekyarov.project.repository.DogRepository;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static rbekyarov.project.web.controllers.DogController.uploadDir;

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
    public void addDog(DogDTO dogDTO, MultipartFile file,String imgName, HttpSession session) throws IOException {

        Dog dogNew = modelMapper.map(dogDTO, Dog.class);

        //
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else {
            imageUUID = imgName;
        }
        dogDTO.setImageName(imageUUID);
        //

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
    public void editDog(Long id, DogEditDTO dogEditDTO, String imgName, MultipartFile file, HttpSession session) throws IOException {

        //image upload
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else {
            imageUUID = imgName;
        }
        dogEditDTO.setImageName(imageUUID);

        // set author
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        dogRepository.editDog(dogEditDTO.getName(),
                formatterLocal(dogEditDTO.getBirthDate()),
                dogEditDTO.getWeight(),
                dogEditDTO.getBreed().getId(),
                dogEditDTO.getSex(),
                dogEditDTO.getPassport(),
                dogEditDTO.getMicrochip(),
                dogEditDTO.getClient().getId(),
                dogEditDTO.getBehavior().getId(),
                dogEditDTO.getImageName(),
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
