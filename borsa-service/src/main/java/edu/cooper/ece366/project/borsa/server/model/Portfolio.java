package edu.cooper.ece366.project.borsa.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "portfolios")
public class Portfolio {
    private static final Logger LOGGER = LoggerFactory.getLogger(Portfolio.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Asset> assets = Collections.synchronizedSet(new HashSet<Asset>());

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userId", nullable = true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Set<Asset> getAssets() {
//        return this.assets;
//    }
//
//    public void setAssets(Set<Asset> assets) {
//        this.assets = assets;
//    }
//
//    public void addAsset(Asset asset) {
//        if (assets == null) {
//            LOGGER.info(String.format("%s has null assets",this.getUser().getId()));
//            HashSet<Asset> aSet = new HashSet<Asset>();
//            assets = Collections.synchronizedSet(aSet);
//        }
//        LOGGER.info(String.format("Adding asset %s to Portfolio %d",asset,this.id));
//        assets.add(asset);
//    }

}
