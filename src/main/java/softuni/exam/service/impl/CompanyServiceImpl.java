package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BehaviorDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.User;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.service.UserService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
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


}


