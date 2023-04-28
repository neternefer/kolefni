package com.kolefni.tracker.repository;

import com.kolefni.tracker.model.Electricity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricityRepository extends JpaRepository<Electricity, Long> {
    List<Electricity> findByUserId(Long id);
}
