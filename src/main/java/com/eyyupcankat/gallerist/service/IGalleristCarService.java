package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.DtoGalleristCar;
import com.eyyupcankat.gallerist.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

}
