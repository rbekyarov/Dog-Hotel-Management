package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.CityDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.CityRepository;
import rbekyarov.project.service.CityService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper, UserService userService) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<City> findAllCityById() {
        return cityRepository.findAllCityById();
    }

    @Override
    public void addCity(CityDTO cityDTO, HttpSession session) {
        City city = modelMapper.map(cityDTO, City.class);
//get and set Author
        city.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        city.setDateCreate(LocalDate.now());
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
    public void editCity(String code, Long id, String name, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        cityRepository.editCity(code, id, name,editAuthorId,dateEdit);
    }
}
