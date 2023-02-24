package rbekyarov.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import rbekyarov.project.base.BaseTest;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.BehaviorRepository;
import rbekyarov.project.service.BehaviorService;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

public class BehaviorServiceTest extends BaseTest {
    private List<Behavior> behaviorList;
    @Autowired
    private BehaviorService behaviorService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HttpSession httpSession;
    @MockBean
    private BehaviorRepository behaviorRepository;

    @Before
    public void setupTest() {
        behaviorList = new ArrayList<>();
        when(behaviorRepository.findAll())
                .thenReturn(behaviorList);
    }
    @Before
    public void   initUserSession() {
        User user = new User();
        user.setUsername("owner");
        httpSession.setAttribute("username", user.getUsername());

    }

    @Test
    public void findAllBehavior_whenNoBehavior_returnEmpty() {
        behaviorList.clear();
        List<Behavior> allBehavior = behaviorService.findAllBehavior();
        Assert.assertTrue(allBehavior.isEmpty());
    }
    @Test
    public void findAllBehavior_AddBehavior() {
        behaviorList.clear();
        BehaviorDTO behaviorDTO = new BehaviorDTO();
        behaviorDTO.setName("test");

        behaviorService.addBehaviors(behaviorDTO,this.httpSession);
        List<Behavior> allBehavior = behaviorService.findAllBehavior();
        Assert.assertTrue(allBehavior.size()==1);
    }


}
