package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Company;

import javax.transaction.Transactional;
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
    @Query("update Company as c SET c.name = :name,c.country=:country ,c.city.id=:cityId,c.address=:address,c.vatNumber=:vatNumber,c.email=:email,c.bankAccount=:bankAccount,c.managerName=:managerName where c.id=:id ")
    void editCompany(@Param("name") String name,
                     @Param("country") String country,
                      @Param("cityId") Long cityId ,
                      @Param("address") String address,
                      @Param("vatNumber") String vatNumber,
                     @Param("email") String email,
                     @Param("bankAccount") String bankAccount,
                     @Param("managerName") String managerName,
                     @Param("id")Long id);

}
