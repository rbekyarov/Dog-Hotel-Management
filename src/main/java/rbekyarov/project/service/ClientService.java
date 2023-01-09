package rbekyarov.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.ClientDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Client;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAllClientById();
    List<Client> findAllClientForReservation();

    void addClient(ClientDTO clientDTO, HttpSession session);

    void removeClientById(Long id);

    Optional<Client> findById(Long id);

    void editClient(String firstName,
                 String lastName,
                 String email,
                 String phone,
                 String address,
                 Long cityId,
                 Long id,
             HttpSession session);

    List<Client> findAll();

    List<Client> listClientByPhone(String clientPhone);

    List<Client> listClientByEmail(String clientEmail);
    Page<Client> findPaginated(Pageable pageable);
}
