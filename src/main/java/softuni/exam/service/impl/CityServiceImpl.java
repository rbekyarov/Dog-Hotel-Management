package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityDTO;
import softuni.exam.models.entity.Breed;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<City> findAllCityById() {
        return cityRepository.findAllCityById();
    }

    @Override
    public void addCity(CityDTO cityDTO) {
        City city = modelMapper.map(cityDTO, City.class);

        cityRepository.save(city);
    }

    @Override
    public void removeCityById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void editCity(String code, Long id, String name) {
        cityRepository.editCity(code, id, name);
    }
}
