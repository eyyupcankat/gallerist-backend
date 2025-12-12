package com.eyyupcankat.gallerist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// aynı galeri aynı arabayı tekrar tekrar kaydedemesin diye
// @UniqueConstraint veriyoruz "gallerist_id","car_id" den
// aynı kayıtları oluşturma demiş oluyoruz
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id","car_id"}
,name = "uq_gallerist_car")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GalleristCar extends BaseEntity {

    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;
}
