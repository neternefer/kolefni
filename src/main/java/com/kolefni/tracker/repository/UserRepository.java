package com.kolefni.tracker.repository;

import com.kolefni.tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long>{
}
