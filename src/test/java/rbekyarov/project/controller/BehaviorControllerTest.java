package rbekyarov.project.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.DogRepository;
import rbekyarov.project.service.BehaviorService;
import rbekyarov.project.web.controllers.BehaviorController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class BehaviorControllerTest {
    private MockMvc mockMvc;
    private BehaviorController behaviorController;

    @Mock
    private BehaviorService behaviorService;
    @Mock
    private DogRepository dogRepository;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        behaviorController = new BehaviorController(behaviorService, dogRepository, session);
        mockMvc = MockMvcBuilders.standaloneSetup(behaviorController).build();
    }

    @Test
    public void testBehaviorTable() throws Exception {
        Page<Behavior> page = new PageImpl<>(Arrays.asList(new Behavior(), new Behavior()));
        when(behaviorService.findPaginated(PageRequest.of(0, 5))).thenReturn(page);

        mockMvc.perform(get("/view/table/behaviorTable"))
//                .andExpect(status().isOk())
 //               .andExpect(view().name("/view/table/behaviorTable"))
                .andExpect(model().attribute("behaviors", page.getContent()));
    }

    @Test
    public void testBehaviorAdd() throws Exception {
        BehaviorDTO behaviorDTO = new BehaviorDTO();
        mockMvc.perform(get("/view/add/behaviorAdd")
                        .flashAttr("behaviorDTO", behaviorDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("/view/add/behaviorAdd"))
                .andExpect(model().attribute("behaviorDTO", behaviorDTO));
    }

    @Test
    public void testAddBehavior() throws Exception {
        BehaviorDTO behaviorDTO = new BehaviorDTO();
        behaviorDTO.setName("testBehavior");
        when(session.getAttribute("user")).thenReturn(new User());
        doNothing().when(behaviorService).addBehavior(behaviorDTO, session);

        mockMvc.perform(post("/view/add/behaviorAdd")
                        .flashAttr("behaviorDTO", behaviorDTO)
                        .sessionAttr("user", new User()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/view/table/behaviorTable"));
    }

    @Test
    public void testRemoveBehavior() throws Exception {
        Behavior behavior = new Behavior();
        behavior.setId(1L);
        when(dogRepository.listBehaviorUsed()).thenReturn(Arrays.asList(behavior));
        mockMvc.perform(get("/view/table/behavior/remove/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/view/table/behaviorTable"))
                .andExpect(flash().attribute("isUsed", true));
        verify(behaviorService, never()).removeBehaviorById(1L);

        when(dogRepository.listBehaviorUsed()).thenReturn(Arrays.asList(new Behavior()));
        mockMvc.perform(get("/view/table/behavior/remove/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/view/table/behaviorTable"));
        verify(behaviorService, times(1)).removeBehaviorById(1L);
    }

    @Test
    public void testGetBehaviorDetail() throws Exception {
        Behavior behavior = new Behavior();
        behavior.setId(1L);
        when(behaviorService.findById(1L)).thenReturn(Optional.of(behavior));
        mockMvc.perform(get("/view/table/behavior/edit/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/view/edit/behaviorEdit"))
                .andExpect(model().attribute("behaviorEditDTO", behavior));
    }

}