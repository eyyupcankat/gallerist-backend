package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.DtoAddress;
import com.eyyupcankat.gallerist.dto.DtoGallerist;
import com.eyyupcankat.gallerist.dto.DtoGalleristIU;
import com.eyyupcankat.gallerist.entity.Address;
import com.eyyupcankat.gallerist.entity.Gallerist;
import com.eyyupcankat.gallerist.exception.BaseException;
import com.eyyupcankat.gallerist.exception.ErrorMessage;
import com.eyyupcankat.gallerist.exception.MessageType;
import com.eyyupcankat.gallerist.repository.AddressRepository;
import com.eyyupcankat.gallerist.repository.GalleristRepository;
import com.eyyupcankat.gallerist.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
        Optional<Address> optionalAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optionalAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist gallerist = new Gallerist();
        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setCreateTime(new Date());
        gallerist.setAddress(optionalAddress.get());

        return gallerist;
    }


    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
        BeanUtils.copyProperties(savedGallerist, dtoGallerist);

        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        dtoGallerist.setDtoAddress(dtoAddress);

        return dtoGallerist;
    }
}
