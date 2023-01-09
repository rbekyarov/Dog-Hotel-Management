package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.*;

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
    public void addDog(DogDTO dogDTO, MultipartFile file, String imgName, HttpSession session) throws IOException {

        Dog dogNew = modelMapper.map(dogDTO, Dog.class);

        // upload image
       String imageUUID = imageUpload(file,imgName);
        dogNew.setImageName(imageUUID);

        //add birthDate
        String date = dogDTO.getBirthDate();
        LocalDate birthDate = formatterLocalForAdd(date);
        dogNew.setBirthDate(birthDate);

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

        //Get new birth Date
        String birthDateDto = dogEditDTO.getBirthDate();
        LocalDate birthDate = formatterLocalForEdit(birthDateDto);

        //image upload
        String imageUUID = imageUpload(file,imgName);
        dogEditDTO.setImageName(imageUUID);

        // set author
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        dogRepository.editDog(dogEditDTO.getName(),
                birthDate,
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
    public List<Dog> listDogByName(String name) {
        return dogRepository.listDogByName(name);
    }

    @Override
    public List<Dog> findAllDogByClient(Long id) {
        return dogRepository.findAllDogByClient(id);
    }

    @Override
    public List<Dog> listDogByClientEmail(String clientEmail) {
        return dogRepository.listDogByClientEmail(clientEmail);
    }

    @Override
    public Page<Dog> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Dog> list;
        List<Dog> dogs = dogRepository.findAll();
        if (dogs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, dogs.size());
            list = dogs.subList(startItem, toIndex);
        }

        Page<Dog> dogsPage = new PageImpl<Dog>(list, PageRequest.of(currentPage, pageSize), dogs.size());

        return dogsPage;
    }

    //convert String to LocalDate
    LocalDate formatterLocalForEdit(String birthDateDto) {
        LocalDate birthDate = null;
        if (birthDateDto.contains("/")) {
            String substring1 = birthDateDto.substring(0, 10);
            String replace = substring1.replace('/', '-');
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1; i++) {
                sb.append(replace.charAt(6));
                sb.append(replace.charAt(7));
                sb.append(replace.charAt(8));
                sb.append(replace.charAt(9));
                sb.append(replace.charAt(5));
                sb.append(replace.charAt(0));
                sb.append(replace.charAt(1));
                sb.append(replace.charAt(5));
                sb.append(replace.charAt(3));
                sb.append(replace.charAt(4));
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(sb.toString(), formatter);
            birthDate = localDate;
        } else {
            birthDate = LocalDate.parse(birthDateDto);
        }

        return birthDate;
    }

    LocalDate formatterLocalForAdd(String birthDateDto) {
        LocalDate birthDate = null;
            String substring1 = birthDateDto.substring(0, 10);
            String replace = substring1.replace('/', '-');
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1; i++) {
                sb.append(replace.charAt(6));
                sb.append(replace.charAt(7));
                sb.append(replace.charAt(8));
                sb.append(replace.charAt(9));
                sb.append(replace.charAt(5));
                sb.append(replace.charAt(3));
                sb.append(replace.charAt(4));
                sb.append(replace.charAt(5));
                sb.append(replace.charAt(0));
                sb.append(replace.charAt(1));
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(sb.toString(), formatter);
            birthDate = localDate;

        return birthDate;
    }
    String imageUpload(MultipartFile file, String imgName) throws IOException {
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        return imageUUID;
    }
}
