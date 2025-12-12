package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.IGalleristCarController;
import com.eyyupcankat.gallerist.dto.DtoGalleristCar;
import com.eyyupcankat.gallerist.dto.DtoGalleristCarIU;
import com.eyyupcankat.gallerist.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gallerist-car")
public class GalleristCarControllerImpl extends BaseController implements IGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public ControllerResponse<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }
}
