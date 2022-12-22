package softuni.exam.service;

import softuni.exam.models.dto.ClientDTO;
import softuni.exam.models.dto.DogDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Client;
import softuni.exam.models.entity.Dog;
import softuni.exam.models.entity.enums.Microchip;
import softuni.exam.models.entity.enums.Passport;
import softuni.exam.models.entity.enums.Sex;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientService {
    List<Client> findAllClientById();

    void addClient(ClientDTO clientDTO);

    void removeClientById(Long id);

    Optional<Client> findById(Long id);

    void editClient(String firstName,
                 String lastName,
                 String email,
                 String phone,
                 String address,
                 Long cityId,
                 Long id);

}
