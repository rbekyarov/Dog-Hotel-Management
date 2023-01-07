package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.City;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City as c order by c.id asc ")
    List<City> findAllCityById();

    Optional<City> findById(Long id);

    @Transactional
    @Modifying
    @Query("update City as c SET c.code = :code , c.name=:name,c.author.id=:editAuthorId,c.dateCreate=:dateEdit,c.dateCreate=:dateEdit where c.id=:id ")
    void editCity(@Param("code") String code,
                  @Param("id") Long id,
                  @Param("name") String name,
                  @Param("editAuthorId") Long editAuthorId,
                  @Param("dateEdit") LocalDate dateEdit);
}
