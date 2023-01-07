package rbekyarov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Role;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("select u from User as u order by u.id asc ")
    List<User> findAllUserById();

    Optional<User> findById(Long id);

    @Transactional
    @Modifying
    @Query("update User as u SET u.role=:role where u.id=:id ")
    void editUser(@Param("role") Role role,
                  @Param("id") Long id);

}
