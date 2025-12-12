package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.DtoGallerist;
import com.eyyupcankat.gallerist.dto.DtoGalleristIU;

public interface IGalleristService {


    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
