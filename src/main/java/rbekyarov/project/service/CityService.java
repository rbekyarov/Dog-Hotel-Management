package rbekyarov.project.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.CityDTO;
import rbekyarov.project.models.dto.restDto.BehaviorRestDTO;
import rbekyarov.project.models.dto.restDto.CityRestDTO;
import rbekyarov.project.models.entity.Behavior;
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
    Page<City> findPaginated(Pageable pageable);

    List<CityRestDTO> findAllCityForRest();

    void deleteByIdForRest(Long id);

    Long createCityForRest(CityRestDTO cityRestDTO);
}
