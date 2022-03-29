package edu.cooper.ece366.project.borsa.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "portfolios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userId")
})
public class UserPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = true)
    private String tickers; // TODO for now, have list of tickers stored common-separated
}
