package com.eyyupcankat.gallerist.repository;

import com.eyyupcankat.gallerist.entity.SaledCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaledCarRepository extends JpaRepository<SaledCar, Long> {

}
