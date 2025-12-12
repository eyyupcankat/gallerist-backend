package com.eyyupcankat.gallerist.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntity{

    private String refreshToken;

    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE_TIME)
    private Date expiredDate;


    @ManyToOne
    private User user;

}
