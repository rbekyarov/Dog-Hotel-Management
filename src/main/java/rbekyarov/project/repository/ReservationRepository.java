package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.models.entity.Reservation;
import rbekyarov.project.models.entity.enums.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation as r order by r.id desc ")
    List<Reservation> findAllReservationByDesc();

    Optional<Reservation> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Reservation as r SET r.client.id =:clientId ,r.dog.id =:dogId,r.startDate=:startDate,r.endDate=:endDate,r.countOvernightStay=:countOvernightStay,r.cell.id=:cellId,r.food=:food,r.deworming=:deworming,r.training=:training,r.bathing=:bathing, r.combing=:combing,r.ears=:ears,r.paws=:paws,r.nails=:nails,r.price=:price,r.discount=:discount,r.totalPrice =:totalPrice,r.statusReservation=:statusReservation, r.author.id=:editAuthorId ,r.dateCreate=:dateEdit, r.company.id=1 where r.id=:id ")
    void editReservation(
            @Param("clientId") Long clientId
            , @Param("dogId") Long dogId
            , @Param("startDate") LocalDate startDate
            , @Param("endDate") LocalDate endDate
            , @Param("countOvernightStay") Integer countOvernightStay
            , @Param("cellId") Long cellId
            , @Param("food") Food food
            , @Param("deworming")  Deworming deworming
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
    @Query("select r from Reservation as r  where r.client.email=:clientEmail order by r.id asc ")
    List<Reservation> listReservationByClientEmail(@Param("clientEmail") String clientEmail);



    @Query("select r from Reservation as r where r.id=:reservationNumber")
    List<Reservation> listReservationById(@Param("reservationNumber") Long reservationNumber);

    @Query("select r.cell from Reservation as r")
    List<Cell> listUsedCell();

    @Query("select r.client from Reservation as r")
    List<Client> listUsedClient();

    @Query("select r.dog from Reservation as r")
    List<Dog> listUsedDog();
    @Query("select r from Reservation as r where r.statusReservation='active'")
    List<Reservation> findAllActiveReservation();
    @Query(nativeQuery = true,
            value = "select * from reservations as r where status_reservation ='active'  limit 3")
//    @Query("select r from Reservation as r where r.statusReservation='active' limit 3")
    List<Reservation> findAllActiveReservationLimit3();

    @Query("select r.dog from Reservation as r where r.statusReservation='active'")
    List<Dog> findActiveReservedDogs();
    @Query(nativeQuery = true,
            value = "select * from reservations as r where status_reservation ='upcoming' order by start_date  limit 3")
//    @Query("select r from Reservation as r where r.statusReservation='upcoming' order by r.endDate limit 3")
    List<Reservation> findAllUpcomingReservations();
}
