package rbekyarov.project.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rbekyarov.project.models.dto.BreedDTO;
import rbekyarov.project.models.entity.Breed;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.repository.DogRepository;
import rbekyarov.project.service.BreedService;
import rbekyarov.project.web.controllers.BreedController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class BreedControllerTest {

    @Mock
    private BreedService breedService;

    @Mock
    private DogRepository dogRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private BreedController breedController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(breedController).build();
    }

    @Test
    public void testBreedTable() throws Exception {
        // Mocking the breed service to return a page of breed entities
        Page<Breed> breeds = new PageImpl<>(Collections.singletonList(new Breed()));
        when(breedService.findPaginated(any(PageRequest.class))).thenReturn(breeds);
        when(session.getAttribute("user")).thenReturn(new User());
        // Sending a GET request to the breedTable endpoint
        mockMvc.perform(get("/view/table/breedTable"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("breeds"))
                .andExpect(model().attributeExists("pageNumbers"))
                .andExpect(view().name("/view/table/breedTable"));
    }

    @Test
    public void testBreedAdd() throws Exception {
        when(session.getAttribute("user")).thenReturn(new User());
        // Sending a GET request to the breedAdd endpoint
        mockMvc.perform(get("/view/add/breedAdd"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("breedDTO"))
                .andExpect(view().name("/view/add/breedAdd"));

        // Mocking a BreedDTO instance
        BreedDTO breedDTO = new BreedDTO();
        breedDTO.setBreedName("Bulldog");

        // Sending a POST request to the breedAdd endpoint with a valid BreedDTO instance
        mockMvc.perform(post("/view/add/breedAdd")
                        .flashAttr("breedDTO", breedDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/view/table/breedTable"));

        // Verifying that the breedService's addBreeds method was called with the BreedDTO instance
        verify(breedService, times(1)).addBreeds(eq(breedDTO), any(HttpSession.class));
    }

    @Test
    public void testRemoveBehavior() throws Exception {
        // Mocking the dogRepository to return a list of Breed entities with one item
        List<Breed> breeds = new ArrayList<>();
        breeds.add(new Breed());
        when(dogRepository.listBreedUsed()).thenReturn(breeds);
        when(session.getAttribute("user")).thenReturn(new User());

        // Sending a GET request to the removeBehavior endpoint with a valid breed ID
        mockMvc.perform(get("/view/table/breed/remove/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/view/table/breedTable"));

        // Verifying that the breedService's removeBreedById method was called with the breed ID
        verify(breedService, times(1)).removeBreedById(eq(1L));

        // Sending a GET request to the removeBehavior endpoint with a breed ID that is used in the dogRepository
        mockMvc.perform(get("/view/table/breed/remove/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/view/table/breedTable"));


    }
}
