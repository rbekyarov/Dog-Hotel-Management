package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Price;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price as p order by p.id asc ")
    List<Price> findAllPricesById();

    Optional<Price> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Price as p SET p.priceOvernightStay = :priceOvernightStay ,p.priceFood =:priceFood,p.priceTraining=:priceTraining,p.priceBathing=:priceBathing,p.priceCombing=:priceCombing,p.pricePaws=:pricePaws,p.priceEars=:priceEars,p.priceNails=:priceNails  where p.id=:id ")
    void editPrice(@Param("priceOvernightStay") BigDecimal priceOvernightStay
            ,@Param("priceFood") BigDecimal priceFood
            ,@Param("priceTraining") BigDecimal priceTraining
            ,@Param("priceBathing") BigDecimal priceBathing
            ,@Param("priceCombing") BigDecimal priceCombing
            ,@Param("pricePaws") BigDecimal pricePaws
            ,@Param("priceEars") BigDecimal priceEars
            ,@Param("priceNails") BigDecimal priceNails
            ,@Param("id") Long id);
}
