package edu.cooper.ece366.project.borsa.server.repository;

import edu.cooper.ece366.project.borsa.server.model.Asset;
import edu.cooper.ece366.project.borsa.server.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<List<Asset>> findByPortfolio(Portfolio portfolio);
    Optional<List<Asset>> findByPortfolioId(Long portfolioId);
}