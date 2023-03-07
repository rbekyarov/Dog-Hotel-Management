package rbekyarov.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rbekyarov.project.models.entity.City;
import rbekyarov.project.models.entity.Company;
import rbekyarov.project.repository.CompanyRepository;
import rbekyarov.project.service.impl.CompanyServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CompanyServiceImplTest {

    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    private List<Company> companies;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        companyService = new CompanyServiceImpl(companyRepository);

        companies = new ArrayList<>();
        companies.add(new Company("Company 1", "logo1.png", "Bulgaria", new City(), "Address 1", "123456789", "email1@test.com", "Bank 1", "1234567890", new BigDecimal("1000.00"), "Manager 1"));
        companies.add(new Company( "Company 2", "logo2.png", "Germany", new City(), "Address 2", "987654321", "email2@test.com", "Bank 2", "0987654321", new BigDecimal("2000.00"), "Manager 2"));
        companies.add(new Company( "Company 3", "logo3.png", "France", new City(), "Address 3", "123456789", "email3@test.com", "Bank 3", "1234509876", new BigDecimal("3000.00"), "Manager 3"));
    }

    @Test
    void testFindAllCompany() {
        when(companyRepository.findAllCompany()).thenReturn(companies);
        List<Company> result = companyService.findAllCompany();
        assertEquals(3, result.size());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Optional<Company> company = Optional.ofNullable(companies.get(0));
        when(companyRepository.findById(id)).thenReturn(company);
        Optional<Company> result = companyService.findById(id);
        assertEquals(company, result);
    }

    @Test
    void testEditCompany() {
        Long id = 1L;
        companyService.editCompany("New Company Name", "new_logo.png", "USA", 4L, "New Address", "111111111", "new_email@test.com", "New Bank", "9876543210", new BigDecimal("5000.00"), "New Manager", id);
        verify(companyRepository, times(1)).editCompany("New Company Name", "new_logo.png", "USA", 4L, "New Address", "111111111", "new_email@test.com", "New Bank", "9876543210", new BigDecimal("5000.00"), "New Manager", id);
    }

    @Test
    void testEditBalance() {
        BigDecimal newBalance = new BigDecimal("5000.00");
        companyService.editBalance(newBalance);
        verify(companyRepository, times(1)).editBalance(newBalance);
    }
    @Test
    void testGetCurrentBalance() {
        BigDecimal expectedBalance = new BigDecimal("6000.00");
        when(companyRepository.getCurrentBalance()).thenReturn(expectedBalance);
        BigDecimal result = companyService.getCurrentBalance();
        assertEquals(expectedBalance, result);
    }
}