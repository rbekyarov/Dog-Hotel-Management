package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.project.models.entity.Vendor;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    @Query("select v from Vendor as v order by v.id asc ")
    List<Vendor> findAllVendor();

    Optional<Vendor> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Vendor as v SET v.name = :name,v.country=:country, v.city.id=:cityId,v.address=:address,v.vatNumber=:vatNumber, v.email=:email, v.author.id=:editAuthorId,v.dateCreate=:dateEdit where v.id=:id ")
    void editVendor(@Param("name") String name,
                      @Param("country") String country ,
                      @Param("cityId") Long cityId ,
                      @Param("address") String address ,
                      @Param("vatNumber") String vatNumber ,
                      @Param("email") String email ,
                      @Param("editAuthorId") Long editAuthorId,
                      @Param("dateEdit") LocalDate dateEdit,
                      @Param("id") Long id );
    @Query("select v from Vendor as v where v.vatNumber=:vendorVatNumber order by v.id asc ")
    List<Vendor> listVendorByVatNumber(@Param("vendorVatNumber")String vendorVatNumber);
    @Query("select v from Vendor as v where v.name=:vendorName order by v.id asc ")
    List<Vendor> listVendorByName(@Param("vendorName")String vendorName);
}
