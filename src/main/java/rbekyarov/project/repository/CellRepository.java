package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.enums.CellSize;
import rbekyarov.project.models.entity.enums.Status;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface CellRepository extends JpaRepository<Cell, Long> {
    @Query("select c from Cell as c order by c.id asc ")
    List<Cell> findAllCellById();

    Optional<Cell> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Cell as c SET c.code = :code ,c.cellSize=:cellSize, c.status=:status, c.author.id=:editAuthorId,c.dateCreate=:dateEdit where c.id=:id ")
    void editCell(@Param("code") String code,
                  @Param("id") Long id,
                  @Param("cellSize") CellSize cellSize,
                  @Param("status") Status status,
                  @Param("editAuthorId") Long editAuthorId,
                  @Param("dateEdit") LocalDate dateEdit);

    @Query("select c from Cell as c where c.status='empty'")
    List<Cell> findAllEmptyCells();

    @Query("select c from Cell as c where c.status='empty' and c.cellSize='SMALL'")
    List<Cell> findAllEmptyCellsSmall();
    @Query("select c from Cell as c where c.status='empty' and c.cellSize='MEDIUM'")
    List<Cell> findAllEmptyCellsMedium();
    @Query("select c from Cell as c where c.status='empty' and c.cellSize='LARGE'")
    List<Cell> findAllEmptyCellsLarge();
    @Transactional
    @Modifying
    @Query("update Cell as c SET c.status='busy' where c.id=:id")
    void setCellBusy(@Param("id") Long id);
    @Transactional
    @Modifying
    @Query("update Cell as c SET c.status='empty' where c.id=:id")
    void setCellEmpty(@Param("id") Long id);
    @Query("select c from Cell as c where c.status = 'empty' and c.status = 'busy' order by c.id asc ")
    List<Cell> findAllCellWithoutCellInService();
}
