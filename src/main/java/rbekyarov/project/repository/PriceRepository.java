package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Price;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price as p order by p.id desc ")
    List<Price> findAllPrices();

    Optional<Price> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Price as p SET p.priceStayS = :priceStayS,p.priceStayM = :priceStayM,p.priceStayL = :priceStayL ,p.priceFood =:priceFood,p.priceDeworming=:priceDeworming,p.priceTraining=:priceTraining,p.priceBathing=:priceBathing,p.priceCombing=:priceCombing,p.pricePaws=:pricePaws,p.priceEars=:priceEars,p.priceNails=:priceNails ,p.dateCreate=:dateEdit where p.id=:id ")
    void editPrice(
             @Param("priceStayS") BigDecimal priceStayS
             ,@Param("priceStayM") BigDecimal priceStayM
             ,@Param("priceStayL") BigDecimal priceStayL
            , @Param("priceFood") BigDecimal priceFood
            , @Param("priceDeworming") BigDecimal priceDeworming
            , @Param("priceTraining") BigDecimal priceTraining
            , @Param("priceBathing") BigDecimal priceBathing
            , @Param("priceCombing") BigDecimal priceCombing
            , @Param("pricePaws") BigDecimal pricePaws
            , @Param("priceEars") BigDecimal priceEars
            , @Param("priceNails") BigDecimal priceNails
            , @Param("id") Long id
            , @Param("dateEdit") LocalDate dateEdit);

    @Query("select p.priceFood from Price as p where p.id= :id")
    BigDecimal getFoodCurrentPrice(@Param("id") Long id);
    @Query("select p.priceDeworming from Price as p where p.id= :id")
    BigDecimal getDewormingCurrentPrice(@Param("id") Long id);

    @Query("select p.priceTraining from Price as p where p.id= :id")
    BigDecimal getTrainingCurrentPrice(@Param("id") Long id);

    @Query("select p.priceBathing from Price as p where p.id= :id")
    BigDecimal getBathingCurrentPrice(@Param("id") Long id);

    @Query("select p.priceCombing from Price as p where p.id= :id")
    BigDecimal getCombingCurrentPrice(@Param("id") Long id);

    @Query("select p.priceEars from Price as p where p.id= :id")
    BigDecimal getEarsCurrentPrice(@Param("id") Long id);

    @Query("select p.pricePaws from Price as p where p.id= :id")
    BigDecimal getPawsCurrentPrice(@Param("id") Long id);

    @Query("select p.priceNails from Price as p where p.id= :id")
    BigDecimal getNailsCurrentPrice(@Param("id") Long id);

    @Query("select p.priceStayS from Price as p where p.id= :id")
    BigDecimal getCurrentPriceStayForCellS(@Param("id") Long id);
    @Query("select p.priceStayM from Price as p where p.id= :id")
    BigDecimal getCurrentPriceStayForCellM(@Param("id") Long id);
    @Query("select p.priceStayL from Price as p where p.id= :id")
    BigDecimal getCurrentPriceStayForCellL(@Param("id") Long id);
}
