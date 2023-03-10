package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Breed;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
    @Query("select b from Breed as b order by b.id asc ")
    List<Breed> findAllBreedById();

    Optional<Breed> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Breed as b SET b.breedName = :name, b.author.id=:editAuthorId,b.dateCreate=:dateEdit where b.id=:id ")
    void editBreed(@Param("name") String name,
                   @Param("id") Long id,
                   @Param("editAuthorId") Long editAuthorId,
                   @Param("dateEdit") LocalDate dateEdit);
}
