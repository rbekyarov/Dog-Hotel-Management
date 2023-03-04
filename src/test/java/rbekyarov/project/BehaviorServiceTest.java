package rbekyarov.project;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import rbekyarov.project.base.BaseTest;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.BehaviorRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import rbekyarov.project.service.BehaviorService;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class BehaviorServiceTest extends BaseTest {

    @MockBean
    private BehaviorRepository behaviorRepository;
    @InjectMocks
    MockHttpSession session;
    @Mock
    private Behavior behavior;

    @Autowired
    private BehaviorService behaviorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Before
    public void   initUserSession() {
        User user = new User();
        user.setUsername("owner");
        this.session.setAttribute("username", user.getUsername());

    }
    @Test
    public void testAddBehavior() {
        // Arrange
        BehaviorDTO behaviorDTO = new BehaviorDTO();
        behaviorDTO.setName("Test");
        behaviorDTO.setId(1l);

        when(behaviorRepository.save(any(Behavior.class))).thenReturn(behavior);

        // Act
        behaviorService.addBehavior(behaviorDTO, session);

        // Assert
        assertEquals(behaviorDTO.getName(),behavior.getName());
        assertEquals(behaviorDTO.getId(),behavior.getId());

    }
}
