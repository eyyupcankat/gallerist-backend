package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.DtoCar;
import com.eyyupcankat.gallerist.dto.DtoCarIU;
import com.eyyupcankat.gallerist.entity.Car;
import com.eyyupcankat.gallerist.repository.CarRepository;
import com.eyyupcankat.gallerist.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {


    @Autowired
    private CarRepository carRepository;

    private Car createCar(DtoCarIU dtoCarIU) {
        Car car = new Car();
        BeanUtils.copyProperties(dtoCarIU, car);
        car.setCreateTime(new Date());
        return car;
    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();

        Car savedCar = carRepository.save(createCar(dtoCarIU));

        BeanUtils.copyProperties(savedCar, dtoCar);

        return dtoCar;
    }
}
