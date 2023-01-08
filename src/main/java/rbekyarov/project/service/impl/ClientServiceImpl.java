package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.ClientDTO;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.CellRepository;
import rbekyarov.project.repository.ClientRepository;
import rbekyarov.project.service.ClientService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final CellRepository cellRepository;
    private final UserService userService;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper,
                             CellRepository cellRepository, UserService userService) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.cellRepository = cellRepository;
        this.userService = userService;
    }

    @Override
    public List<Client> findAllClientById() {
        return clientRepository.findAllClientById();
    }

    @Override
    public List<Client> findAllClientForReservation() {
        return clientRepository.findAllClientForReservation();
    }

    @Override
    public void addClient(ClientDTO clientDTO, HttpSession session) {
        Client client = modelMapper.map(clientDTO, Client.class);
        //get and set Author
        client.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        client.setDateCreate(LocalDate.now());
        clientRepository.save(client);
    }

    @Override
    public void removeClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void editClient(String firstName, String lastName, String email, String phone, String address, Long cityId, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        clientRepository.editClient(firstName,
                lastName,
                email,
                phone,
                address,
                cityId,
                id,
                editAuthorId,
                dateEdit);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

}