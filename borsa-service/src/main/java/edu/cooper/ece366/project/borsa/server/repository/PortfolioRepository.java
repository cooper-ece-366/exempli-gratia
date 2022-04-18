package edu.cooper.ece366.project.borsa.server.repository;

import edu.cooper.ece366.project.borsa.server.model.Portfolio;
import edu.cooper.ece366.project.borsa.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByUser(User user);
    Optional<Portfolio> findByUserId(Long userId);
    Optional<Portfolio> findFirstBy();

    @Transactional
    void deleteByUserId(Long userId);
}