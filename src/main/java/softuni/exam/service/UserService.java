package softuni.exam.service;

import softuni.exam.models.dto.UserDTO;
import softuni.exam.models.dto.UserRegisterDTO;
import softuni.exam.models.entity.User;
import softuni.exam.models.entity.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    UserDTO loginUser(UserDTO userDTO);


    List<User> findAllUserById();

    Optional<User> findById(Long id);

    void editUser(Role role, Long id);

    void addUser(UserDTO userDTO);

    void removeUserById(Long id);

    Optional<User> findByUsername(String username);
}
