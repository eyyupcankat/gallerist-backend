package com.eyyupcankat.gallerist.dto;

import lombok.Data;

@Data
public class DtoAddress extends BaseDto{

    private String city;

    private String district;

    private String neighborhood;

    private String street;
}
