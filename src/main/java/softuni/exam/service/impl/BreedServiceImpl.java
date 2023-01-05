package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BreedDTO;
import softuni.exam.models.entity.Breed;
import softuni.exam.models.entity.User;
import softuni.exam.repository.BreedRepository;
import softuni.exam.service.BreedService;
import softuni.exam.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BreedServiceImpl implements BreedService {
    private final BreedRepository breedRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public BreedServiceImpl(BreedRepository breedRepository, ModelMapper modelMapper, UserService userService) {
        this.breedRepository = breedRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<Breed> findAllBreedById() {
        return breedRepository.findAllBreedById();
    }

    @Override
    public void addBreeds(BreedDTO breedDTO, HttpSession session) {
        Breed breed = modelMapper.map(breedDTO, Breed.class);
        breed.setBreedName(breedDTO.getBreedName());
        //get and set Author
        breed.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        breed.setDateCreate(LocalDate.now());
        breedRepository.save(breed);
    }

    @Override
    public void removeBreedById(Long id) {
        breedRepository.deleteById(id);
    }

    @Override
    public Optional<Breed> findById(Long id) {
        return breedRepository.findById(id);
    }

    @Override
    public void editBreeds(String name, Long id, HttpSession session) {
        //Edit Author
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        breedRepository.editBreed(name, id,editAuthorId,dateEdit);
    }
}
