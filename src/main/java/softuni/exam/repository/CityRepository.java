package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.enums.Status;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City as c order by c.id asc ")
    List<City> findAllCityById();

    Optional<City> findById(Long id);

    @Transactional
    @Modifying
    @Query("update City as c SET c.code = :code , c.name=:name,c.author.id=:editAuthorId where c.id=:id ")
    void editCity(@Param("code") String code, @Param("id") Long id, @Param("name") String name,@Param("editAuthorId") Long editAuthorId);
}
