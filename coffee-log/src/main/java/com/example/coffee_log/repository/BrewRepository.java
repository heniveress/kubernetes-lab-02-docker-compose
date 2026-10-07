package com.example.coffee_log.repository;

import com.example.coffee_log.model.Brew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrewRepository extends JpaRepository<Brew, Long> {

    List<Brew> findAllByOrderByIdAsc();

    @Query("select b.capsuleName as capsuleName, count(b) as total from Brew b group by b.capsuleName")
    List<CapsuleCount> countByCapsule();

    @Query("select avg(b.intensity) from Brew b")
    Double averageIntensity();

    interface CapsuleCount {
        String getCapsuleName();
        Long getTotal();
    }
}
