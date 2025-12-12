package com.eyyupcankat.gallerist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    private String firstName;

    private String lastName;

    private String tckn;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date birthDate;

    @OneToOne
    private Address address;

    @OneToOne
    private Account account;

}
