package rbekyarov.project.service.impl;

import org.springframework.stereotype.Service;
import rbekyarov.project.models.entity.Company;
import rbekyarov.project.repository.CompanyRepository;
import rbekyarov.project.service.CompanyService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;


    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;

    }
    @Override
    public List<Company> findAllCompany() {
        return companyRepository.findAllCompany();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public void editCompany(String name, String logoName, String country, Long citId, String address, String vatNumber, String email, String bankName, String bankAccount, BigDecimal balance, String managerName, Long id) {
        companyRepository.editCompany(
                name,
                logoName,
                country,
                citId,
                address,
                vatNumber,
                email,
                bankName,
                bankAccount,
                balance,
                managerName,
                id);
    }

    @Override
    public void editBalance(BigDecimal newBalance) {
        companyRepository.editBalance(newBalance);
    }

    @Override
    public BigDecimal getCurrentBalance() {
        return companyRepository.getCurrentBalance();
    }


}


