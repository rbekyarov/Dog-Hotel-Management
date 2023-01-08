package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.enums.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation as r order by r.id asc ")
    List<Reservation> findAllReservationById();

    Optional<Reservation> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Reservation as r SET r.client.id =:clientId ,r.dog.id =:dogId,r.startDate=:startDate,r.endDate=:endDate,r.countOvernightStay=:countOvernightStay,r.cell.id=:cellId,r.food=:food,r.training=:training,r.bathing=:bathing, r.combing=:combing,r.ears=:ears,r.paws=:paws,r.nails=:nails,r.price=:price,r.discount=:discount,r.totalPrice =:totalPrice,r.statusReservation=:statusReservation, r.author.id=:editAuthorId ,r.dateCreate=:dateEdit, r.company.id=1 where r.id=:id ")
    void editReservation(
            @Param("clientId") Long clientId
            , @Param("dogId") Long dogId
            , @Param("startDate") LocalDate startDate
            , @Param("endDate") LocalDate endDate
            , @Param("countOvernightStay") Integer countOvernightStay
            , @Param("cellId") Long cellId
            , @Param("food") Food food
            , @Param("training") Training training
            , @Param("bathing") Bathing bathing
            , @Param("combing") Combing combing
            , @Param("ears") Ears ears
            , @Param("paws") Paws paws
            , @Param("nails") Nails nails
            , @Param("price") BigDecimal price
            , @Param("discount") Double discount
            , @Param("totalPrice") BigDecimal totalPrice
            , @Param("statusReservation") StatusReservation statusReservation
            , @Param("id") Long id
            , @Param("editAuthorId") Long editAuthorId
            , @Param("dateEdit") LocalDate dateEdit);

    @Transactional
    @Modifying
    @Query("update Reservation as r SET r.statusReservation =:statusReservation   where r.id=:reservationId ")
    void updateStatusReservation(@Param("reservationId") Long reservationId,
                                 @Param("statusReservation") StatusReservation statusReservation);

    @Transactional
    @Modifying
    @Query("update Reservation as r SET r.invoiced =:invoiced where r.id=:reservationId ")
    void changeInvoiced(@Param("reservationId") Long reservationId,
                        @Param("invoiced") Invoiced invoiced);
}