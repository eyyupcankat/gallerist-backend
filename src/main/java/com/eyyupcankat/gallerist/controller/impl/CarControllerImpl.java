package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.ICarController;
import com.eyyupcankat.gallerist.dto.DtoCar;
import com.eyyupcankat.gallerist.dto.DtoCarIU;
import com.eyyupcankat.gallerist.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarControllerImpl extends BaseController implements ICarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public ControllerResponse<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }
}
