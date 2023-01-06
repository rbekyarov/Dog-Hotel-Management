package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Company;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company as c order by c.id asc ")
    List<Company> findAllCompany();

    Optional<Company> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Company as c SET c.name = :name,c.logoName=:logoName,c.country=:country ,c.city.id=:cityId,c.address=:address,c.vatNumber=:vatNumber,c.email=:email,c.bankName=:bankName,c.bankAccount=:bankAccount,c.balance=:balance,c.managerName=:managerName where c.id=:id ")
    void editCompany(@Param("name") String name,
                     @Param("logoName") String logoName,
                     @Param("country") String country,
                     @Param("cityId") Long cityId,
                     @Param("address") String address,
                     @Param("vatNumber") String vatNumber,
                     @Param("email") String email,
                     @Param("bankName") String bankName,
                     @Param("bankAccount") String bankAccount,
                     @Param("balance") BigDecimal balance,
                     @Param("managerName") String managerName,
                     @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Company as c SET c.balance=:newBalance where c.id=1 ")
    void editBalance(@Param("newBalance") BigDecimal newBalance);

    @Query("select c.balance from Company as c where c.id=1 ")
    public BigDecimal getCurrentBalance();
}
