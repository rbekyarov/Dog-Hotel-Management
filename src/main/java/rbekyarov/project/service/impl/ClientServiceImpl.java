package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.ClientDTO;
import rbekyarov.project.models.dto.restDto.*;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.ClientType;
import rbekyarov.project.repository.CellRepository;
import rbekyarov.project.repository.CityRepository;
import rbekyarov.project.repository.ClientRepository;
import rbekyarov.project.service.ClientService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final CellRepository cellRepository;
    private final UserService userService;
    private final CityRepository cityRepository;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper,
                             CellRepository cellRepository, UserService userService,
                             CityRepository cityRepository) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.cellRepository = cellRepository;
        this.userService = userService;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Client> findAllClientByDesc() {
        return clientRepository.findAllClientByDesc();
    }

    @Override
    public List<Client> findAllClientForReservation() {
        return clientRepository.findAllClientForReservation();
    }

    @Override
    public void addClient(ClientDTO clientDTO, HttpSession session) {
        Client client = modelMapper.map(clientDTO, Client.class);
        client.setCity(cityRepository.findById(clientDTO.getCityId()).orElseThrow());
        //get and set Author
        client.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        client.setDateCreate(LocalDate.now());
        client.setId(null);
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
    public void editClient(String firstName, String lastName, String email, String phone, String address, ClientType clientType, Long cityId, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        clientRepository.editClient(firstName,
                lastName,
                email,
                phone,
                address,
                clientType,
                cityId,
                id,
                editAuthorId,
                dateEdit);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> listClientByPhone(String clientPhone) {
        return clientRepository.listClientByPhone(clientPhone);
    }

    @Override
    public List<Client> listClientByEmail(String clientEmail) {
        return clientRepository.listClientByEmail(clientEmail);
    }

    @Override
    public Page<Client> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Client> list;
        List<Client> clients = clientRepository.findAllClientByDesc();
        if (clients.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clients.size());
            list = clients.subList(startItem, toIndex);
        }

        Page<Client> clientsPage = new PageImpl<Client>(list, PageRequest.of(currentPage, pageSize), clients.size());

        return clientsPage;
    }

    @Override
    public List<ClientRestDTO> findAllClientForRest() {
        return clientRepository.findAll().
                stream().
                map(this::map).
                toList();
    }
    private ClientRestDTO map(Client client) {

        ClientRestDTO clientRestDTO = new ClientRestDTO();
        clientRestDTO.setId(client.getId());
        clientRestDTO.setFirstName(client.getFirstName());
        clientRestDTO.setLastName(client.getLastName());
        clientRestDTO.setEmail(client.getEmail());
        clientRestDTO.setPhone(client.getPhone());
        clientRestDTO.setAddress(client.getAddress());
        clientRestDTO.setClientType(client.getClientType());

        CityRestThinDTO cityRestDTO = new CityRestThinDTO();
        cityRestDTO.setName(client.getCity().getName());


        clientRestDTO.setCity(cityRestDTO);

        Set<Dog> dogs = client.getDogs();
        Set<DogRestThinDTO> dogRestDTOSet = new HashSet<>();
        for (Dog dog : dogs) {
            DogRestThinDTO dogRestThinDTO = new DogRestThinDTO();

            dogRestThinDTO.setName(dog.getName());
            dogRestDTOSet.add(dogRestThinDTO);
        }
        clientRestDTO.setDogs(dogRestDTOSet);

        return clientRestDTO;
    }

}
