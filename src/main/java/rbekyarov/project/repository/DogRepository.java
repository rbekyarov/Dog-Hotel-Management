package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.models.entity.enums.Microchip;
import rbekyarov.project.models.entity.enums.Passport;
import rbekyarov.project.models.entity.enums.Sex;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository

public interface DogRepository extends JpaRepository<Dog, Long> {
    @Query("select d from Dog as d order by d.id asc ")
    List<Dog> findAllDogById();

    Optional<Dog> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Dog as d SET d.name = :name, d.birthDate=:birthDate, d.weight =:weight, d.breed.id = :breedId,d.sex=:sex,d.passport=:passport,d.microchip=:microchip,d.client.id = :clientId, d.behavior.id=:behaviorId,d.imageName=:imageName, d.author.id=:editAuthorId,d.dateCreate=:dateEdit where d.id=:id ")
    void editDog(@Param("name") String name,
                 @Param("birthDate") LocalDate birthDate,
                 @Param("weight") Integer weight,
                 @Param("breedId") Long breedId,
                 @Param("sex") Sex sex,
                 @Param("passport") Passport passport,
                 @Param("microchip") Microchip microchip,
                 @Param("clientId") Long clientId,
                 @Param("behaviorId") Long behaviorId,
                 @Param("imageName")  String imageName,
                 @Param("id") Long id,
                 @Param("editAuthorId") Long editAuthorId,
                 @Param("dateEdit") LocalDate dateEdit);

    @Query("select d.id from Dog as d where d.client.email=:clientEmail")
    Set<Long> listDogIds(@Param("clientEmail") String clientEmail);

    @Query("select d from Dog as d where d.client.email=:clientEmail")
    List<Dog> listDogByEmail(@Param("clientEmail") String email);

    @Query("select d from Dog as d where d.client.id=:id ")
    List<Dog> findAllDogByClient(@Param("id") Long id);
}
