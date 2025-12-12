package com.eyyupcankat.gallerist.repository;

import com.eyyupcankat.gallerist.entity.Gallerist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristRepository extends JpaRepository<Gallerist, Long> {



}
