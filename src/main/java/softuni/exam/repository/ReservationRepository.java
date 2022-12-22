package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.Dog;
import softuni.exam.models.entity.Price;
import softuni.exam.models.entity.Reservation;
import softuni.exam.models.entity.enums.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation as r order by r.id asc ")
    List<Reservation> findAllReservationById();

    Optional<Reservation> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Reservation as r SET r.client.id =:clientId ,r.dogs =:dogs,r.startDate=:startDate,r.endDate=:endDate,r.cells=:cells,r.food=:food,r.training=:training,r.bathing=:bathing, r.combing=:combing,r.ears=:ears,r.paws=:paws,r.nails=:nails,r.discount=:discount  where r.id=:id ")
    void editReservation(@Param("clientId") Long clientId
            ,@Param("dogs") Set<Dog> dogs
            ,@Param("startDate") LocalDate startDate
            ,@Param("endDate") LocalDate endDate
            ,@Param("cells") Set<Cell> cells
            ,@Param("food") Food food
            ,@Param("training") Training training
            ,@Param("bathing") Bathing bathing
            ,@Param("combing") Combing combing
            ,@Param("ears") Ears ears
            ,@Param("paws") Paws paws
            ,@Param("nails") Nails nails
            ,@Param("discount") Double discount
            ,@Param("id") Long id);
}
