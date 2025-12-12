package com.eyyupcankat.gallerist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gallerist extends BaseEntity {

    private String firstName;

    private String lastName;

    @OneToOne
    private Address address;



}
