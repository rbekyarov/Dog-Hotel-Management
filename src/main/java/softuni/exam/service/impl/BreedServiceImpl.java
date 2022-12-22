package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BreedDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Breed;
import softuni.exam.repository.BreedRepository;
import softuni.exam.service.BreedService;

import java.util.List;
import java.util.Optional;

@Service
public class BreedServiceImpl implements BreedService {
    private final BreedRepository breedRepository;
    private final ModelMapper modelMapper;

    public BreedServiceImpl(BreedRepository breedRepository, ModelMapper modelMapper) {
        this.breedRepository = breedRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Breed> findAllBreedById() {
        return breedRepository.findAllBreedById();
    }

    @Override
    public void addBreeds(BreedDTO breedDTO) {
        Breed breed = modelMapper.map(breedDTO, Breed.class);
        breed.setBreedName(breedDTO.getBreedName());
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
    public void editBreeds(String name, Long id) {
        breedRepository.editBreed(name, id);
    }
}
