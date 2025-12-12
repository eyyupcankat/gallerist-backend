package com.eyyupcankat.gallerist.repository;

import com.eyyupcankat.gallerist.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {


}
