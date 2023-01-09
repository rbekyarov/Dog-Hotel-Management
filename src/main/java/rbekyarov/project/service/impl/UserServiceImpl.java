package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.UserDTO;
import rbekyarov.project.models.dto.UserRegisterDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Role;
import rbekyarov.project.repository.UserRepository;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        userRepository.saveAndFlush(user);

    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) {
        return this.userRepository.findByUsername(userDTO.getUsername())
                .filter(u -> u.getPassword().equals(userDTO.getPassword()))
                .map(u -> this.modelMapper.map(u, UserDTO.class))
                .orElse(null);
    }

    @Override
    public List<User> findAllUserById() {
        return userRepository.findAllUserById();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void editUser(Role role, Long id) {
        userRepository.editUser(role, id);
    }


    public void addUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        userRepository.save(user);
    }

    @Override
    public Page<User> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;
        List<User> users = userRepository.findAllUserById();
        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }

        Page<User> usersPage = new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), users.size());

        return usersPage;
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getAuthorFromSession(HttpSession session) {
        Object userName = session.getAttribute("username");
        Optional<User> userOptional = userRepository.findByUsername(userName.toString());
        User user = userOptional.get();
        return user;
    }


}


