package rbekyarov.project.service;


import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.VendorDTO;
import rbekyarov.project.models.dto.restDto.CityRestThinDTO;
import rbekyarov.project.models.dto.restDto.VendorRestDTO;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.Vendor;
import rbekyarov.project.repository.VendorRepository;
import rbekyarov.project.service.UserService;
import rbekyarov.project.service.impl.VendorServiceImpl;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTest {
    @Mock
    private VendorRepository vendorRepository;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;
    @Mock
    HttpSession httpSession;
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private VendorServiceImpl vendorService;
    private Vendor vendor1;

    private Vendor vendor2;
    @BeforeEach
    public void setUp() {
        vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Vendor1");

        vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Vendor2");
    }

    @Test
    public void testFindAllVendor() {
        List<Vendor> vendors = new ArrayList<>();
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Vendor 1");
        vendors.add(vendor);
        when(vendorRepository.findAllVendor()).thenReturn(vendors);

        List<Vendor> result = vendorService.findAllVendor();

        assertEquals(vendors, result);
    }

    @Test
    public void testAddVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor 1");

        HttpSession session = mock(HttpSession.class);
        User author = new User();
        author.setId(1L);
        when(userService.getAuthorFromSession(session)).thenReturn(author);

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Vendor 1");
        when(modelMapper.map(vendorDTO, Vendor.class)).thenReturn(vendor);

        vendorService.addVendor(vendorDTO, session);

        assertTrue(vendor.getId() > 0);
        assertEquals(author, vendor.getAuthor());
        assertEquals(LocalDate.now(), vendor.getDateCreate());
    }

    @Test
    public void testRemoveVendorById() {
        Long id = 1L;
        vendorService.removeVendorById(id);
        verify(vendorRepository).deleteById(id);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Vendor vendor = new Vendor();
        vendor.setId(id);
        when(vendorRepository.findById(id)).thenReturn(Optional.of(vendor));

        Optional<Vendor> result = vendorService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void testEditVendor() {
        Long id = 1L;
        String name = "New Vendor Name";
        String country = "New Vendor Country";
        Long cityId = 2L;
        String address = "New Vendor Address";
        String vatNumber = "New Vendor VAT Number";
        String email = "New Vendor Email";

        User user = new User();
        user.setId(1L);
        when(userService.getAuthorFromSession(httpSession)).thenReturn(user);

        LocalDate dateEdit = LocalDate.now();

        vendorService.editVendor(name, country, cityId, address, vatNumber, email, httpSession, id);

        verify(vendorRepository).editVendor(
                stringArgumentCaptor.capture(),
                stringArgumentCaptor.capture(),
                longArgumentCaptor.capture(),
                stringArgumentCaptor.capture(),
                stringArgumentCaptor.capture(),
                stringArgumentCaptor.capture(),
                longArgumentCaptor.capture(),
                any(LocalDate.class),
                longArgumentCaptor.capture()
        );

        assertEquals(name, stringArgumentCaptor.getAllValues().get(0));
        assertEquals(country, stringArgumentCaptor.getAllValues().get(1));
        assertEquals(cityId, longArgumentCaptor.getAllValues().get(0));
        assertEquals(address, stringArgumentCaptor.getAllValues().get(2));
        assertEquals(vatNumber, stringArgumentCaptor.getAllValues().get(3));
        assertEquals(email, stringArgumentCaptor.getAllValues().get(4));
        assertEquals(user.getId(), longArgumentCaptor.getAllValues().get(1));
        assertEquals(id, longArgumentCaptor.getAllValues().get(2));
    }
    @Test
    public void testListVendorByVatNumber() {
        when(vendorRepository.listVendorByVatNumber("123456")).thenReturn(Arrays.asList(vendor1));

        List<Vendor> vendors = vendorService.listVendorByVatNumber("123456");

        Assertions.assertEquals(1, vendors.size());
        Assertions.assertEquals(vendor1, vendors.get(0));
    }
    @Test
    public void testListVendorByName() {
        when(vendorRepository.listVendorByName("Vendor1")).thenReturn(Arrays.asList(vendor1));

        List<Vendor> vendors = vendorService.listVendorByName("Vendor1");

        Assertions.assertEquals(1, vendors.size());
        Assertions.assertEquals(vendor1, vendors.get(0));
    }
    @Test
    public void testFindPaginated() {
        int pageSize = 1;
        Pageable pageable = PageRequest.of(0, pageSize);
        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);
        Page<Vendor> expectedPage = new PageImpl<Vendor>(vendors, pageable, vendors.size());

        when(vendorRepository.findAllVendor()).thenReturn(vendors);

        Page<Vendor> page = vendorService.findPaginated(pageable);

        Assertions.assertEquals(expectedPage.getTotalPages(), page.getTotalPages());
        Assertions.assertEquals(expectedPage.getContent().get(0), page.getContent().get(0));
    }
}