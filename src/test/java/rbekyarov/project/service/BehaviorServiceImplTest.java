package rbekyarov.project.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.BehaviorRepository;
import rbekyarov.project.service.impl.BehaviorServiceImpl;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BehaviorServiceImplTest {

    @Mock
    private BehaviorRepository behaviorRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private BehaviorServiceImpl behaviorService;

    @Test
    public void testFindAllBehavior() {
        Behavior behavior = new Behavior();
        behavior.setName("test behavior");
        List<Behavior> behaviorList = Collections.singletonList(behavior);

        when(behaviorRepository.findAllOrderById()).thenReturn(behaviorList);

        List<Behavior> result = behaviorService.findAllBehavior();

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("test behavior", result.get(0).getName());
    }

    @Test
    public void testAddBehavior() {
        BehaviorDTO behaviorDTO = new BehaviorDTO();
        behaviorDTO.setName("test behavior");

        User user = new User();
        user.setId(1L);
        when(userService.getAuthorFromSession(httpSession)).thenReturn(user);

        Behavior behavior = new Behavior();
        when(modelMapper.map(behaviorDTO, Behavior.class)).thenReturn(behavior);

        behaviorService.addBehavior(behaviorDTO, httpSession);

        verify(behaviorRepository).save(behavior);

        Assert.assertEquals("test behavior", behavior.getName());
        Assert.assertEquals(user, behavior.getAuthor());
    }

    @Test
    public void testRemoveBehaviorById() {
        behaviorService.removeBehaviorById(1L);
        verify(behaviorRepository).deleteById(1L);
    }

    @Test
    public void testFindById() {
        Behavior behavior = new Behavior();
        behavior.setId(1L);
        Optional<Behavior> optionalBehavior = Optional.of(behavior);

        when(behaviorRepository.findById(1L)).thenReturn(optionalBehavior);

        Optional<Behavior> result = behaviorService.findById(1L);

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(1L, result.get().getId().longValue());
    }

    @Test
    public void testEditBehaviors() {
        User user = new User();
        user.setId(1L);
        when(userService.getAuthorFromSession(httpSession)).thenReturn(user);

        behaviorService.editBehavior("test behavior", 1L, httpSession);

        verify(behaviorRepository).editBehavior("test behavior", 1L, 1L, LocalDate.now());
    }

    @Test
    public void testFindPaginated() {
        Behavior behavior1 = new Behavior();
        behavior1.setId(1L);
        behavior1.setName("Behavior 1");

        Behavior behavior2 = new Behavior();
        behavior2.setId(2L);
        behavior2.setName("Behavior 2");

        Behavior behavior3 = new Behavior();
        behavior3.setId(3L);
        behavior3.setName("Behavior 3");

        List<Behavior> behaviorList = Arrays.asList(behavior1, behavior2, behavior3);
        when(behaviorRepository.findAllOrderById()).thenReturn(behaviorList);

        Pageable pageable = PageRequest.of(0, 2);
        Page<Behavior> result = behaviorService.findPaginated(pageable);

        Assert.assertEquals(2, result.getNumberOfElements());
        Assert.assertEquals(1L, result.getContent().get(0).getId().longValue());
        Assert.assertEquals(2L, result.getContent().get(1).getId().longValue());
    }

}