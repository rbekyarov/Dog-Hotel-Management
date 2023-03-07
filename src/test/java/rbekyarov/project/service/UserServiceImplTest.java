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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import rbekyarov.project.models.dto.UserDTO;
import rbekyarov.project.models.dto.UserRegisterDTO;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Role;
import rbekyarov.project.repository.UserRepository;
import rbekyarov.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
        when(modelMapper.map(userRegisterDTO, User.class)).thenReturn(user);
        when(passwordEncoder.encode(userRegisterDTO.getPassword())).thenReturn("testEncodedPassword");

        userService.registerUser(userRegisterDTO);

        verify(userRepository, Mockito.times(1)).saveAndFlush(user);
        Assertions.assertEquals("testEncodedPassword", user.getPassword());
    }

    @Test
    public void testLoginUser() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        User result = userService.loginUser(userDTO);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testAuthenticate() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("testPassword", user.getPassword())).thenReturn(true);

        boolean result = userService.authenticate("testUser", "testPassword");

        Assertions.assertTrue(result);
    }

    @Test
    public void testFindAllUserById() {
        when(userRepository.findAllUserById()).thenReturn(new ArrayList<>());

        List<User> result = userService.findAllUserById();

        Assertions.assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1L);

        Assertions.assertEquals(Optional.of(user), result);
    }

    @Test
    public void testEditUser() {
        userService.editUser(Role.ADMIN, 1L);

        verify(userRepository, Mockito.times(1)).editUser(Role.ADMIN, 1L);
    }
    @Test
    void testEditUserPassword() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("johndoe");
        userDTO.setPassword("password");
        Long userId = 1L;


        when(passwordEncoder.encode(anyString())).thenReturn("encrypted_password");
        userService.editUserPassword(userDTO, userId);

        verify(userRepository).editUserPassword("encrypted_password", userId, "johndoe");
    }
    @Test
    void testAddUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("johndoe");
        userDTO.setPassword("password");

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword("encrypted_password");

        when(modelMapper.map(any(UserDTO.class), any())).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encrypted_password");
        userService.addUser(userDTO);

        verify(userRepository).saveAndFlush(user);
    }

    @Test
    public void testFindPaginated() {
        // create a mock list of users
        List<User> mockUsers = Arrays.asList(
                new User( "test1", "password1", Role.USER),
                new User( "test2", "password2", Role.ADMIN),
                new User( "test3", "password3", Role.USER)
        );

        // mock the behavior of the userRepository
        when(userRepository.findAllUserById()).thenReturn(mockUsers);

        // create a Pageable object for testing
        Pageable pageable = PageRequest.of(0, 2);

        // call the method being tested
        Page<User> result = userService.findPaginated(pageable);

        // assert the results

        assertThat(result.getContent().get(0).getUsername()).isEqualTo("test1");
        assertThat(result.getContent().get(1).getUsername()).isEqualTo("test2");
        assertThat(result.getTotalElements()).isEqualTo(3);
    }
    @Test
    public void testRemoveUserById() {
        // create a mock user id
        Long id = 1L;

        // call the method being tested
        userService.removeUserById(id);

        // verify that the userRepository was called with the correct arguments
        verify(userRepository).deleteById(id);
    }
    @Test
    public void testFindByUsername() {
        // create a mock user
        User mockUser = new User( "test", "password", Role.USER);

        // mock the behavior of the userRepository
        when(userRepository.findByUsername("test")).thenReturn(Optional.of(mockUser));

        // call the method being tested
        Optional<User> result = userService.findByUsername("test");

        // assert the results
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("test");
        assertThat(result.get().getPassword()).isEqualTo("password");
        assertThat(result.get().getRole()).isEqualTo(Role.USER);
    }
    @Test
    public void testGetAuthorFromSession() {
        // create a mock session
        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute("username")).thenReturn("test");

        // create a mock user
        User mockUser = new User( "test", "password", Role.USER);

        // mock the behavior of the userRepository
        when(userRepository.findByUsername("test")).thenReturn(Optional.of(mockUser));

        // call the method being tested
        User result = userService.getAuthorFromSession(mockSession);

        // assert the results
        assertThat(result.getUsername()).isEqualTo("test");
        assertThat(result.getPassword()).isEqualTo("password");
        assertThat(result.getRole()).isEqualTo(Role.USER);
    }
}
