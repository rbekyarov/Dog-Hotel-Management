package rbekyarov.project.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.DogRepository;
import rbekyarov.project.service.BehaviorService;
import rbekyarov.project.web.controllers.BehaviorController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

@RunWith(MockitoJUnitRunner.class)
public class BehaviorControllerTest {
    private MockMvc mockMvc;
    private BehaviorController behaviorController;

    @Mock
    private BehaviorService behaviorService;
    @Mock
    private ModelAndView modelAndView;
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
    public void testRemoveBehavior() throws Exception {
        Behavior behavior = new Behavior();
        behavior.setName("testBehavior");
        behavior.setId(1L);
        when(session.getAttribute("user")).thenReturn(new User());

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


}