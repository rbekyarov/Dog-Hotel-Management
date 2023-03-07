package rbekyarov.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import rbekyarov.project.models.dto.UserDTO;
import rbekyarov.project.models.dto.UserRegisterDTO;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Role;
import rbekyarov.project.repository.UserRepository;
import rbekyarov.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private HttpSession session;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;
    private UserRegisterDTO userRegisterDTO;
    private Page<User> userPage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setPassword("testPassword");

        userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("testUser");
        userRegisterDTO.setPassword("testPassword");

        List<User> users = new ArrayList<>();
        users.add(user);

        userPage = new PageImpl<>(users);
    }

    @Test
    public void testRegisterUser() {
        Mockito.when(modelMapper.map(userRegisterDTO, User.class)).thenReturn(user);
        Mockito.when(passwordEncoder.encode(userRegisterDTO.getPassword())).thenReturn("testEncodedPassword");

        userService.registerUser(userRegisterDTO);

        Mockito.verify(userRepository, Mockito.times(1)).saveAndFlush(user);
        Assertions.assertEquals("testEncodedPassword", user.getPassword());
    }

    @Test
    public void testLoginUser() {
        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        User result = userService.loginUser(userDTO);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testAuthenticate() {
        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches("testPassword", user.getPassword())).thenReturn(true);

        boolean result = userService.authenticate("testUser", "testPassword");

        Assertions.assertTrue(result);
    }

    @Test
    public void testFindAllUserById() {
        Mockito.when(userRepository.findAllUserById()).thenReturn(new ArrayList<>());

        List<User> result = userService.findAllUserById();

        Assertions.assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testFindById() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1L);

        Assertions.assertEquals(Optional.of(user), result);
    }

    @Test
    public void testEditUser() {
        userService.editUser(Role.ADMIN, 1L);

        Mockito.verify(userRepository, Mockito.times(1)).editUser(Role.ADMIN, 1L);
    }

}
