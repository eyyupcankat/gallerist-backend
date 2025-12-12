package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.DtoGallerist;
import com.eyyupcankat.gallerist.dto.DtoGalleristIU;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface IGalleristController {

    public ControllerResponse<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU);
}
