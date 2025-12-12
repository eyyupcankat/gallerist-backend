package com.eyyupcankat.gallerist.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{

    private String city;

    private String district;

    private String neighborhood;

    private String street;



}
