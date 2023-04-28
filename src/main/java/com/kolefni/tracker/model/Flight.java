package com.kolefni.tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(nullable = false, length = 10)
    private double distance;

    @Getter
    @Setter
    @Column(name = "dept", nullable = false, length = 5)
    private String depAirport;

    @Getter
    @Setter
    @Column(name = "dest", nullable = false, length = 5)
    private String destAirport;

    @Getter
    @Setter
    @Column(name = "class", nullable = false, length = 10)
    private String classType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Flight(double distance,
                  String depAirport,
                  String destAirport,
                  String classType,
                  User user) {
        this.distance = distance;
        this.depAirport = depAirport;
        this.destAirport = destAirport;
        this.classType = classType;
        this.user = user;
    }
}
