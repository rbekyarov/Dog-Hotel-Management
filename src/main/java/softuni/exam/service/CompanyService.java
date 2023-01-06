package softuni.exam.service;

import softuni.exam.models.dto.BehaviorDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Company;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company>findAllCompany();

    Optional<Company> findById(Long id);

    void editCompany(String name, String logoName, String country, Long citId, String address, String vatNumber, String email, String bankName, String bankAccount, BigDecimal balance, String managerName, Long id);

    void editBalance(BigDecimal newBalance);
    public BigDecimal getCurrentBalance();
}
