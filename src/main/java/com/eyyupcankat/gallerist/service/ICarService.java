package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.DtoCar;
import com.eyyupcankat.gallerist.dto.DtoCarIU;
import com.eyyupcankat.gallerist.entity.Car;

public interface ICarService {


    public DtoCar saveCar(DtoCarIU  dtoCarIU);
}
