package suharik.apps.universitymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import suharik.apps.universitymanagement.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE TYPE(u) = :clazz")
    List<User> findUsersByRole(@Param("clazz") Class<? extends User> clazz);
    Optional<User> findByEmail(String email);
}
