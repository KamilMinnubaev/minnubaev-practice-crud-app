package ru.arapov.minnubaevpracticecrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arapov.minnubaevpracticecrud.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
