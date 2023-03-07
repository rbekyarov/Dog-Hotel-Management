package rbekyarov.project.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import rbekyarov.project.models.dto.ClientDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.ClientType;
import rbekyarov.project.repository.CellRepository;
import rbekyarov.project.repository.CityRepository;
import rbekyarov.project.repository.ClientRepository;
import rbekyarov.project.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CellRepository cellRepository;

    @Mock
    private UserService userService;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void testFindAllClientByDesc() {
        // arrange
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());
        when(clientRepository.findAllClientByDesc()).thenReturn(clients);

        // act
        List<Client> result = clientService.findAllClientByDesc();

        // assert
        assertEquals(2, result.size());
        assertEquals(clients, result);
        verify(clientRepository, Mockito.times(1)).findAllClientByDesc();
    }

    @Test
    public void testFindAllClientForReservation() {
        // arrange
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());
        when(clientRepository.findAllClientForReservation()).thenReturn(clients);

        // act
        List<Client> result = clientService.findAllClientForReservation();

        // assert
        assertEquals(2, result.size());
        assertEquals(clients, result);
        verify(clientRepository, Mockito.times(1)).findAllClientForReservation();
    }

    @Test
    public void testAddClient() {
        ClientDTO clientDTO = new ClientDTO();
        Client client = new Client();
        HttpSession session = mock(HttpSession.class);

        when(modelMapper.map(clientDTO, Client.class)).thenReturn(client);
        when(cityRepository.findById(clientDTO.getCityId())).thenReturn(Optional.of(new City()));

        clientService.addClient(clientDTO, session);

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void testRemoveClientById() {
        // arrange
        Long id = 1L;

        // act
        clientService.removeClientById(id);

        // assert
        verify(clientRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testFindById() {
        // arrange
        Long id = 1L;
        Client client = new Client();
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        // act
        Optional<Client> result = clientService.findById(id);

        // assert
        assertTrue(result.isPresent());
        assertEquals(client, result.get());
        verify(clientRepository, Mockito.times(1)).findById(id);
    }
    @Test
    public void testEditClient() {
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String phone = "+1234567890";
        String address = "123 Main St";
        ClientType clientType = ClientType.VIP;
        Long cityId = 1L;
        Long id = 1L;
        HttpSession session = mock(HttpSession.class);
        User author = new User();

        when(userService.getAuthorFromSession(session)).thenReturn(author);

        clientService.editClient(firstName, lastName, email, phone, address, clientType, cityId, id, session);

        verify(clientRepository, times(1)).editClient(firstName, lastName, email, phone, address, clientType, cityId, id, author.getId(), LocalDate.now());
    }
    @Test
    public void testFindAll() {
        Client client1 = new Client();
        Client client2 = new Client();

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<Client> clients = clientService.findAll();

        assertEquals(2, clients.size());
    }
    @Test
    public void testListClientByPhone() {
        String phone = "+1234567890";
        Client client = new Client();

        when(clientRepository.listClientByPhone(phone)).thenReturn(Arrays.asList(client));

        List<Client> clients = clientService.listClientByPhone(phone);

        assertEquals(1, clients.size());
    }
    @Test
    public void testListClientByEmail() {
        String email = "johndoe@example.com";
        Client client = new Client();

        when(clientRepository.listClientByEmail(email)).thenReturn(Arrays.asList(client));

        List<Client> clients = clientService.listClientByEmail(email);

        assertEquals(1, clients.size());
    }


}
