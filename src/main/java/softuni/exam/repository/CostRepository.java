package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Cost;
import softuni.exam.models.entity.Price;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface CostRepository extends JpaRepository<Cost, Long> {

    @Query("select c from Cost as c order by c.id asc ")
    List<Cost> findAllCost();

    Optional<Cost> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Cost as c SET c.vendor.id = :vendorId , c.description=:description,c.amount=:amount,c.dateCost=:dateCost,c.author.id=:editAuthorId,c.dateCreate=:dateEdit,c.dateCreate=:dateEdit where c.id=:id ")
    void editCost(@Param("vendorId") Long vendorId,
                  @Param("description") String description,
                  @Param("amount") BigDecimal amount,
                  @Param("dateCost") LocalDate dateCost,
                  @Param("editAuthorId") Long editAuthorId,
                  @Param("dateEdit") LocalDate dateEdit,
                  @Param("id") Long id);
}
