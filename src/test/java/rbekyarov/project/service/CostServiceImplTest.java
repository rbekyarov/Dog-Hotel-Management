package rbekyarov.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockHttpSession;
import rbekyarov.project.models.dto.CostDTO;
import rbekyarov.project.models.dto.restDto.CostRestDTO;
import rbekyarov.project.models.dto.restDto.VendorRestThinDTO;
import rbekyarov.project.models.entity.Cost;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.Vendor;
import rbekyarov.project.repository.CostRepository;
import rbekyarov.project.service.CompanyService;
import rbekyarov.project.service.UserService;
import rbekyarov.project.service.impl.CostServiceImpl;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CostServiceImplTest {

    private CostServiceImpl costService;

    @Mock
    private CostRepository costRepository;

    @Mock
    private UserService userService;

    @Mock
    private CompanyService companyService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private HttpSession session;
    @Mock
    private HttpSession httpSession;

    private DateTimeFormatter formatter;
    private Cost cost;
    private CostDTO costDTO;
    @BeforeEach
    public void setUp() {

            cost = new Cost();
            cost.setId(1L);
            cost.setDescription("Test Description");
            cost.setInvoiceNo("INV-0001");
            cost.setAmount(BigDecimal.valueOf(100.00));
            cost.setDateCost(LocalDate.parse("2022-01-01"));
            cost.setVendor(new Vendor());
            costDTO = new CostDTO();
            costDTO.setDescription("Test Description");
            costDTO.setInvoiceNo("INV-0001");
            costDTO.setAmount(BigDecimal.valueOf(100.00));
            costDTO.setDateCost("2022-01-01");

        MockitoAnnotations.initMocks(this);
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.costService = new CostServiceImpl(costRepository, new ModelMapper(), userService, companyService);
    }
    @Test
   public void addCost() {
        // given
//        CostDTO costDTO = new CostDTO();
//        costDTO.setDateCost("1.01.23 г.");
//        costDTO.setAmount(BigDecimal.valueOf(50.00));
//        Cost cost = new Cost();
//        cost.setDateCost(LocalDate.now());
        when(modelMapper.map(costDTO, Cost.class)).thenReturn(cost);
        when(costRepository.save(cost)).thenReturn(cost);
        when(userService.getAuthorFromSession(session)).thenReturn(new User());
        when(companyService.getCurrentBalance()).thenReturn(BigDecimal.valueOf(100));
        // when
        costService.addCost(costDTO, session);
        // then
        //verify(costRepository, times(1)).save(cost);
        verify(companyService, times(1)).editBalance(any());
    }
    @Test
    public void testFindAllCostByDesc() {
        List<Cost> expectedCosts = Arrays.asList(new Cost(), new Cost());
        when(costRepository.findAllCostByDesc()).thenReturn(expectedCosts);

        List<Cost> actualCosts = costService.findAllCostByDesc();

        verify(costRepository, times(1)).findAllCostByDesc();
        assertThat(actualCosts).isEqualTo(expectedCosts);
    }


    @Test
    void removeCostByIdTest() {
        when(costRepository.findById(anyLong())).thenReturn(Optional.of(cost));
        when(companyService.getCurrentBalance()).thenReturn(BigDecimal.valueOf(1000.00));

        costService.removeCostById(1L);

        verify(costRepository, times(1)).deleteById(anyLong());
        verify(companyService, times(1)).editBalance(any(BigDecimal.class));
    }

    @Test
    void findByIdTest() {
        when(costRepository.findById(anyLong())).thenReturn(Optional.of(cost));

        Optional<Cost> result = costService.findById(1L);

        assertEquals(result.get(), cost);
    }

    @Test
    public void testEditCost() {
        // arrange
        Long vendorId = 1L;
        String description = "Test cost";
        String invoiceNo = "INV-123";
        BigDecimal amount = BigDecimal.valueOf(100.00);
        String dateCost = "2023-03-07";
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setId(1L);
        when(userService.getAuthorFromSession(session)).thenReturn(user);
        Long id = 1L;
        Cost cost = new Cost();
        cost.setAmount(BigDecimal.valueOf(50.00));
        when(costRepository.findById(id)).thenReturn(Optional.of(cost));
        BigDecimal currentBalance = BigDecimal.valueOf(500.00);
        when(companyService.getCurrentBalance()).thenReturn(currentBalance);

        // act
        costService.editCost(vendorId, description, invoiceNo, amount, dateCost, session, id);

        // assert
        verify(userService).getAuthorFromSession(session);
       // verify(companyService).getCurrentBalance();
        verify(companyService).editBalance(currentBalance.add(BigDecimal.valueOf(50.00)));
        verify(companyService).editBalance(currentBalance.subtract(amount));
        verify(costRepository).editCost(vendorId, description, invoiceNo, amount, LocalDate.parse(dateCost), 1L, LocalDate.now(), id);
    }
    @Test
    public void testFindPaginated() {
        // arrange
        int pageSize = 10;
        int currentPage = 0;
        List<Cost> costs = new ArrayList<>();
        Cost cost1 = new Cost();
        cost1.setId(1L);
        Cost cost2 = new Cost();
        cost2.setId(2L);
        costs.add(cost1);
        costs.add(cost2);
        when(costRepository.findAllCostByDesc()).thenReturn(costs);

        // act
        Page<Cost> result = costService.findPaginated(PageRequest.of(currentPage, pageSize));

        // assert
        verify(costRepository).findAllCostByDesc();
        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getNumber());
        assertEquals(10, result.getSize());
    }
    @Test
    public void testFindCostByVendor() {
        // arrange
        String vendorName = "Test vendor";
        List<Cost> costs = new ArrayList<>();
        Cost cost1 = new Cost();
        cost1.setId(1L);
        Cost cost2 = new Cost();
        cost2.setId(2L);
        costs.add(cost1);
        costs.add(cost2);
        when(costRepository.findCostByVendor(vendorName)).thenReturn(costs);

        // act
        List<Cost> result = costService.findCostByVendor(vendorName);

        // assert
        verify(costRepository).findCostByVendor(vendorName);
        assertEquals(2, result.size());
    }
    @Test
    public void testGetTotalAmountCost() {
        List<Cost> costList = Arrays.asList(
                new Cost( new Vendor(),"desc","inv1",new BigDecimal("100"), LocalDate.now() , new User(),LocalDate.now() ),
                new Cost( new Vendor(),"desc","inv1",new BigDecimal("100"), LocalDate.now() , new User(),LocalDate.now() ),
                new Cost( new Vendor(),"desc","inv1",new BigDecimal("100"), LocalDate.now() , new User(),LocalDate.now() )

        );
        Mockito.when(costRepository.findAllCostByDesc()).thenReturn(costList);
        BigDecimal totalAmount = costService.getTotalAmountCost();
        assertEquals(new BigDecimal("300"), totalAmount);
    }

    @Test
    public void testFindLast2Cost() {
        List<Cost> costList = Arrays.asList(
                new Cost( new Vendor(),"desc","inv1",new BigDecimal("100"), LocalDate.now() , new User(),LocalDate.now() ),
                new Cost( new Vendor(),"desc","inv1",new BigDecimal("200"), LocalDate.now() , new User(),LocalDate.now() )
        );
        Mockito.when(costRepository.findLast2Cost()).thenReturn(costList);
        List<Cost> last2Cost = costService.findLast2Cost();
        assertEquals(2, last2Cost.size());
        assertEquals(new BigDecimal("100"), last2Cost.get(0).getAmount());
        assertEquals(new BigDecimal("200"), last2Cost.get(1).getAmount());
    }
    @Test
    public void testGetAllCostForRest() {
        List<Cost> costList = Arrays.asList(
                new Cost( new Vendor(),"desc1","123",new BigDecimal("100"), LocalDate.now() , new User(),LocalDate.now() ),
                new Cost( new Vendor(),"desc2","456",new BigDecimal("200"), LocalDate.now() , new User(),LocalDate.now() )
        );
        List<CostRestDTO> costRestDTOList = Arrays.asList(
                new CostRestDTO(1L,new VendorRestThinDTO("vendor1"),"desc1","123", new BigDecimal("100"), LocalDate.now().toString() ),
                new CostRestDTO(2L,new VendorRestThinDTO("vendor2"),"desc2","456", new BigDecimal("200"), LocalDate.now().toString() )
        );
        Mockito.when(costRepository.findAll()).thenReturn(costList);
        Mockito.when(modelMapper.map(Mockito.any(Cost.class), Mockito.eq(CostRestDTO.class))).thenReturn(costRestDTOList.get(0), costRestDTOList.get(1));
        List<CostRestDTO> allCostForRest = costService.getAllCostForRest();
        assertEquals(2, allCostForRest.size());
        assertEquals("desc1", allCostForRest.get(0).getDescription());
       // assertEquals("vendor1", allCostForRest.get(0).getVendor().getName());
        assertEquals("desc2", allCostForRest.get(1).getDescription());
        //assertEquals("vendor2", allCostForRest.get(1).getVendor().getName());
    }
    @Test
    public void testFormatterLocalDate() {
        String dateDTO = "1.01.23 г.";
        LocalDate date = costService.formatterLocalDate(dateDTO);
        assertEquals("2023-01-01",date.toString());

    }
    @Test
    public void testMap() {

        CostRestDTO target  = costService.map(cost);
        assertEquals(target.getId(),cost.getId());
        assertEquals(target.getDateCost().toString(),cost.getDateCost().toString());
        assertEquals(target.getAmount(),cost.getAmount());


    }
}
