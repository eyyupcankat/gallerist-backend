package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.IGalleristController;
import com.eyyupcankat.gallerist.dto.DtoGallerist;
import com.eyyupcankat.gallerist.dto.DtoGalleristIU;
import com.eyyupcankat.gallerist.repository.GalleristRepository;
import com.eyyupcankat.gallerist.service.IGalleristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gallerist")
public class GalleristControllerImpl extends BaseController implements IGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping("/save")
    @Override
    public ControllerResponse<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.saveGallerist(dtoGalleristIU));
    }
}
