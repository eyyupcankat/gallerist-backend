package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.ISaledCarController;
import com.eyyupcankat.gallerist.dto.DtoSaledCar;
import com.eyyupcankat.gallerist.dto.DtoSaledCarIU;
import com.eyyupcankat.gallerist.service.ISaledCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saled-car")
public class SaledCarControllerImpl extends BaseController implements ISaledCarController {

    @Autowired
    private ISaledCarService saledCarService;

    @PostMapping("/save")
    @Override
    public ControllerResponse<DtoSaledCar> saleCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
        return ok(saledCarService.buyCar(dtoSaledCarIU));
    }
}
