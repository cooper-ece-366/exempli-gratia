package edu.cooper.ece366.project.borsa.server.repository;

import edu.cooper.ece366.project.borsa.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    // Optional<User> findById(Long id);
    Boolean existsByEmail(String email);
}