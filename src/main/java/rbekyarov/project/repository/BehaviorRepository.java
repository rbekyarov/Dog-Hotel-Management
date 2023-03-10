package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Behavior;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BehaviorRepository extends JpaRepository<Behavior, Long> {

    @Query("select b from Behavior as b order by b.id asc ")
    List<Behavior> findAllOrderById();

    Optional<Behavior> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Behavior as b SET b.name = :name, b.author.id=:editAuthorId,b.dateCreate=:dateEdit where b.id=:id ")
    void editBehavior(@Param("name") String name,
                      @Param("id") Long id ,
                      @Param("editAuthorId") Long editAuthorId,
                      @Param("dateEdit") LocalDate dateEdit);


}
