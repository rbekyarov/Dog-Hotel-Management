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
import rbekyarov.project.models.dto.DogDTO;
import rbekyarov.project.models.entity.enums.DogSize;
import rbekyarov.project.service.*;
import rbekyarov.project.repository.DogRepository;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    public List<Dog> findAllDogByDesc() {
        return dogRepository.findAllDogByDesc();
    }

    @Override
    public List<Dog> findAll() {
        return dogRepository.findAll();
    }

    @Override
    public void addDog(DogDTO dogDTO, MultipartFile file, String imgName, HttpSession session) throws IOException {

        Dog dogNew = modelMapper.map(dogDTO, Dog.class);

        // upload image
       String imageUUID = imageUpload(file,imgName);
       if(imageUUID.isEmpty()){
           imageUUID="emptyDog.png";
       }
        dogNew.setImageName(imageUUID);

        //add birthDate
        String birthDateDto = dogDTO.getBirthDate();
        LocalDate birthDate = null;
        if(birthDateDto.isEmpty()){
            dogNew.setBirthDate(null);
        }else {
             birthDate = formatterLocalDate(birthDateDto);
            dogNew.setBirthDate(birthDate);
        }


        //add lastDewormingDate

        String lastDewormingDateDro = dogDTO.getLastDewormingDate();
        if (lastDewormingDateDro.isEmpty()){
            dogNew.setLastDewormingDate(null);
        }else {
            LocalDate lastDewormingDate = formatterLocalDate(lastDewormingDateDro);
            dogNew.setLastDewormingDate(lastDewormingDate);
        }



        //get and set Author
        dogNew.setAuthor(userService.getAuthorFromSession(session));

        // set dateCreated
        dogNew.setDateCreate(LocalDate.now());
        //set DogSize
        Integer weight = dogDTO.getWeight();
        if (weight <= 10) {
            dogNew.setDogSize(DogSize.SMALL);
        } else if (weight <= 20) {
            dogNew.setDogSize(DogSize.MEDIUM);
        }else {
            dogNew.setDogSize(DogSize.LARGE);
        }
        //set years
        if(birthDate==null){
            dogNew.setYears(null);
        }else {
            long daysLong = ChronoUnit.DAYS.between(birthDate,LocalDate.now());
            int years = (int)daysLong / 365;
            int months = ((int)daysLong % 365)/30 ;
            String result="";
            if (years<1){
                result = String.format("mos."+months);
            }else {
                result = String.format("yrs."+years+" mos."+months);
            }
            dogNew.setYears(result);
        }



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

        //add birthDate
        String birthDateDto = dogEditDTO.getBirthDate();
        LocalDate birthDate = formatterLocalDate(birthDateDto);

        //add lastDewormingDate
        String lastDewormingDateDro = dogEditDTO.getLastDewormingDate();
        LocalDate lastDewormingDate = null;
        if(!lastDewormingDateDro.isEmpty()){
            lastDewormingDate = formatterLocalDate(lastDewormingDateDro);
        }



        //image upload
        String imageUUID = imageUpload(file,imgName);
        dogEditDTO.setImageName(imageUUID);

        // set author
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        //set dogSize
        Integer weight = dogEditDTO.getWeight();
        DogSize dogSize;
        if (weight <= 10) {
            dogSize=DogSize.SMALL;
        } else if (weight <= 20) {
            dogSize=DogSize.MEDIUM;
        }else {
            dogSize=DogSize.LARGE;
        }

        //set years
        long daysLong = ChronoUnit.DAYS.between(birthDate,LocalDate.now());
        int years = (int)daysLong / 365;
        int months = ((int)daysLong % 365)/30 ;
        String result="";
        if (years<1){
            result = String.format("mos."+months);
        }else {
            result = String.format("yrs."+years+" mos."+months);
        }
        //

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
                dateEdit,
                dogSize,
                result,
                lastDewormingDate,
                dogEditDTO.getMicrochipNumber());
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
        return clientService.findAllClientByDesc();
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
        List<Dog> dogs = dogRepository.findAllDogByDesc();
        if (dogs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, dogs.size());
            list = dogs.subList(startItem, toIndex);
        }

        Page<Dog> dogsPage = new PageImpl<Dog>(list, PageRequest.of(currentPage, pageSize), dogs.size());

        return dogsPage;
    }

    @Override
    public Integer getWeightById(Long id) {
        return dogRepository.getWeightById(id);
    }

    @Override
    public void updateDogYears() {
        List<Dog> dogs = findAll();
        for (Dog dog : dogs) {
            String result="";
            if(dog.getBirthDate()==null){
                result="";
            }else {
                long daysLong = ChronoUnit.DAYS.between(dog.getBirthDate(),LocalDate.now());
                int years = (int)daysLong / 365;
                int months = ((int)daysLong % 365)/30 ;

                if (years<1){
                    result = String.format("mos."+months);
                }else {
                    result = String.format("yrs."+years+" mos."+months);
                }
            }

            dogRepository.editDogYearsById(dog.getId(),result);
        }


    }


    //convert String to LocalDate

    LocalDate formatterLocalDate(String dateDto) {
        //1.01.23 г.  ->23-01-01
        //11.01.23 г. ->23-01-11
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        if(dateDto.contains(".")){
          if(dateDto.length()==10){
              sb.append("2");
              sb.append("0");
              sb.append(dateDto.charAt(5));
              sb.append(dateDto.charAt(6));
              sb.append("-");
              sb.append(dateDto.charAt(2));
              sb.append(dateDto.charAt(3));
              sb.append("-");
              sb.append("0");
              sb.append(dateDto.charAt(0));

          }else if(dateDto.length()==11){
              sb.append("2");
              sb.append("0");
              sb.append(dateDto.charAt(6));
              sb.append(dateDto.charAt(7));
              sb.append("-");
              sb.append(dateDto.charAt(3));
              sb.append(dateDto.charAt(4));
              sb.append("-");
              sb.append(dateDto.charAt(0));
              sb.append(dateDto.charAt(1));
            }
            String s = sb.toString();

            LocalDate localDate = LocalDate.parse(s, formatter);
            return localDate;
        }else {
            LocalDate localDate = LocalDate.parse(dateDto, formatter);
            return localDate;
        }
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
