package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.DtoGalleristCar;
import com.eyyupcankat.gallerist.dto.DtoGalleristCarIU;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface IGalleristCarController {


    public ControllerResponse<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU);
}
