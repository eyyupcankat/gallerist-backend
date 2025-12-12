package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.DtoSaledCar;
import com.eyyupcankat.gallerist.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
