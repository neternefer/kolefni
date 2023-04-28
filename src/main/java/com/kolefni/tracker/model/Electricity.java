package com.kolefni.tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kolefni.tracker.enums.Units;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "electricity")
public class Electricity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Units unit;

    @Getter
    @Setter
    @Column(nullable = false, length = 10)
    private double amount;

    @Getter
    @Setter
    @Column(nullable = false, length = 50)
    private String country;

    @Getter
    @Setter
    @Column(nullable = false, length = 10)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Getter
    @Setter
    @Column(nullable = false, length = 10)
    private double footprint;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Electricity(Units unit, double amount, String country, LocalDate date, double footprint, User user) {
        this.unit = unit;
        this.amount = amount;
        this.country = country;
        this.date = date;
        this.footprint = footprint;
        this.user = user;
    }

    public Electricity(Units unit, double amount, String country) {
        this.unit = unit;
        this.amount = amount;
        this.country = country;
    }
}
