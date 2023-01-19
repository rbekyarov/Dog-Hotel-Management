package rbekyarov.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.UserDTO;
import rbekyarov.project.models.dto.UserRegisterDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Role;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);
     boolean authenticate(String username, String password);

    User loginUser(UserDTO userDTO);


    List<User> findAllUserById();

    Optional<User> findById(Long id);

    void editUser(Role role, Long id);
     void editUserPassword(UserDTO userDTO, Long id);

    void addUser(UserDTO userDTO);
    Page<User> findPaginated(Pageable pageable);

    void removeUserById(Long id);

    Optional<User> findByUsername(String username);

     User getAuthorFromSession(HttpSession session);
}
