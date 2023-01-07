package rbekyarov.project.service;


import rbekyarov.project.models.dto.CityDTO;
import rbekyarov.project.models.entity.City;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> findAllCityById();

    void addCity(CityDTO cityDTO, HttpSession session);

    void removeCityById(Long id);

    Optional<City> findById(Long id);

    void editCity(String code, Long id , String name, HttpSession session);
}
