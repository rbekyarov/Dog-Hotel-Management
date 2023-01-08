package rbekyarov.project.service;

import rbekyarov.project.models.dto.UserDTO;
import rbekyarov.project.models.dto.UserRegisterDTO;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Role;

import javax.servlet.http.HttpSession;
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

     User getAuthorFromSession(HttpSession session);
}