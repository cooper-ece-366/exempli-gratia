package edu.cooper.ece366.project.borsa.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "assets")
public class Asset implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private String name;

    @Column(nullable = false)
    private String ticker;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "portfolioId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Portfolio portfolio;

    public Asset() {}

    public Asset(String ticker) {
        this.ticker = ticker;
    }

    public Portfolio getPortfolio() { return this.portfolio; }

    public void setPortfolio(Portfolio portfolio) { this.portfolio = portfolio; }

    public String getTicker() {
        return(this.ticker);
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
