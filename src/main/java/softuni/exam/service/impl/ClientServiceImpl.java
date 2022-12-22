package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ClientDTO;
import softuni.exam.models.entity.Client;
import softuni.exam.models.entity.Dog;
import softuni.exam.repository.ClientRepository;
import softuni.exam.service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Client> findAllClientById() {
        return clientRepository.findAllClientById();
    }

    @Override
    public void addClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);

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
    public void editClient(String firstName, String lastName, String email, String phone, String address, Long cityId, Long id) {
       clientRepository.editClient(firstName,
               lastName,
               email,
               phone,
               address,
               cityId,
               id);
    }


}
