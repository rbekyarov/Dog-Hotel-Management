package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Client;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select c from Client as c order by c.id asc ")
    List<Client> findAllClientById();

    Optional<Client> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Client as c SET c.firstName = :firstName, c.lastName=:lastName,c.email=:email, c.phone =:phone, c.address = :address,c.city.id=:cityId,c.author.id=:editAuthorId,c.dateCreate=:dateEdit where c.id=:id ")
    void editClient(@Param("firstName") String firstName,
                    @Param("lastName") String lastName,
                    @Param("email") String email,
                    @Param("phone") String phone,
                    @Param("address") String address,
                    @Param("cityId") Long cityId,
                    @Param("id") Long id,
                    @Param("editAuthorId") Long editAuthorId,
                    @Param("dateEdit") LocalDate dateEdit);
//    @Query("select c.id,c.firstName,c.lastName,c.email,d.id,d.name from Client as c  join Dog as d on c.id = d.client.id  ")
    List<Client> findAllByDogsIsNotNull();
@Query(value ="select c.id,c.first_name,  c.last_name, d.id,d.name  from clients as c join dogs as d on c.id=d.client_id", nativeQuery = true)
    List<Client> findAllClientForReservation();
}
