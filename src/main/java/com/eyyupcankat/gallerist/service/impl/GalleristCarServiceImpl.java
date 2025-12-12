package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.*;
import com.eyyupcankat.gallerist.entity.Car;
import com.eyyupcankat.gallerist.entity.Gallerist;
import com.eyyupcankat.gallerist.entity.GalleristCar;
import com.eyyupcankat.gallerist.exception.BaseException;
import com.eyyupcankat.gallerist.exception.ErrorMessage;
import com.eyyupcankat.gallerist.exception.MessageType;
import com.eyyupcankat.gallerist.repository.CarRepository;
import com.eyyupcankat.gallerist.repository.GalleristCarRepository;
import com.eyyupcankat.gallerist.repository.GalleristRepository;
import com.eyyupcankat.gallerist.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar galleristCar = new GalleristCar();

        Optional<Gallerist> optionalGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if (optionalGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGalleristId().toString()));
        }

        Optional<Car> optionalCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGalleristId().toString()));
        }

        galleristCar.setGallerist(optionalGallerist.get());
        galleristCar.setCar(optionalCar.get());
        galleristCar.setCreateTime(new Date());

        return galleristCar;
    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoCar dtoCar = new DtoCar();
        DtoGallerist dtoGallerist = new DtoGallerist();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);

        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
        dtoGallerist.setDtoAddress(dtoAddress);


        BeanUtils.copyProperties(savedGalleristCar,dtoGalleristCar);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);
        dtoGalleristCar.setCreateTime(new Date());
        return dtoGalleristCar;
    }
}
