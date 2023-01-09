package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.BreedDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Breed;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.BreedRepository;
import rbekyarov.project.service.BreedService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
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

    @Override
    public Page<Breed> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Breed> list;
        List<Breed> breeds = breedRepository.findAll();
        if (breeds.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, breeds.size());
            list = breeds.subList(startItem, toIndex);
        }

        Page<Breed> breedsPage = new PageImpl<Breed>(list, PageRequest.of(currentPage, pageSize), breeds.size());

        return breedsPage;
    }
}
