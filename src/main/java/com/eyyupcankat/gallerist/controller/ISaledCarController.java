package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.DtoSaledCar;
import com.eyyupcankat.gallerist.dto.DtoSaledCarIU;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface ISaledCarController {

    public ControllerResponse<DtoSaledCar> saleCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU);
}
