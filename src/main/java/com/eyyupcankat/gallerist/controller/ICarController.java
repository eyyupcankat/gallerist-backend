package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.DtoCar;
import com.eyyupcankat.gallerist.dto.DtoCarIU;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICarController {


    public ControllerResponse<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU);
}
