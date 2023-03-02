package rbekyarov.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import rbekyarov.project.base.BaseTest;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.BehaviorRepository;
import rbekyarov.project.service.BehaviorService;

import java.util.List;

@RunWith(SpringRunner.class)
public class BehaviorServiceTest extends BaseTest {

    @Autowired
     BehaviorService behaviorService;

    @InjectMocks
    MockHttpSession session;
    @MockBean
    BehaviorRepository mockBehaviorRepository;


    @Before
    public void   initUserSession() {
        User user = new User();
        user.setUsername("owner");
        this.session.setAttribute("username", user.getUsername());

    }
    @Test
    public void findAllBehaviorReturnEmpty() {

        List<Behavior> allBehavior = behaviorService.findAllBehavior();
        Assert.assertTrue(allBehavior.isEmpty());
    }
    @Test
    public void addBehavior() {

        BehaviorDTO behaviorDTO = new BehaviorDTO();
        behaviorDTO.setName("test");
        behaviorDTO.setId(1l);

        this.behaviorService.addBehavior(behaviorDTO,this.session);
        List<Behavior> allBehavior = behaviorService.findAllBehavior();
        Assert.assertTrue(allBehavior.size()==1);
    }


}
