package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.*;
import com.eyyupcankat.gallerist.entity.Car;
import com.eyyupcankat.gallerist.entity.Customer;
import com.eyyupcankat.gallerist.entity.Gallerist;
import com.eyyupcankat.gallerist.entity.SaledCar;
import com.eyyupcankat.gallerist.enums.CarStatusType;
import com.eyyupcankat.gallerist.exception.BaseException;
import com.eyyupcankat.gallerist.exception.ErrorMessage;
import com.eyyupcankat.gallerist.exception.MessageType;
import com.eyyupcankat.gallerist.repository.CarRepository;
import com.eyyupcankat.gallerist.repository.CustomerRepository;
import com.eyyupcankat.gallerist.repository.GalleristRepository;
import com.eyyupcankat.gallerist.repository.SaledCarRepository;
import com.eyyupcankat.gallerist.service.ICurrencyRatesService;
import com.eyyupcankat.gallerist.service.ISaledCarService;
import com.eyyupcankat.gallerist.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    public BigDecimal convertCustomerAmountToUSD(Customer customer) {
        // burada DateUtils.getCurrentDate() ile o anın tarihi veriliyor
        // fakat tarihin hafta sonu veya resmi tatil olması durumunda
        // mb veri yayınlamadığından null dönüp bizi patlatır
        // bunu bilerek kullan
        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(), DateUtils.getCurrentDate());
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 5, BigDecimal.ROUND_HALF_UP);
        return customerUSDAmount;
    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

        Optional<Customer> optionalCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoSaledCarIU.getCustomerId().toString()));
        }
        Optional<Car> optionalCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoSaledCarIU.getCarId().toString()));
        }

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optionalCustomer.get());
        if (customerUSDAmount.compareTo(optionalCar.get().getPrice())==0 || customerUSDAmount.compareTo(optionalCar.get().getPrice())>0) {
            return true;
        }
        return false;
    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {

        Optional<Gallerist> optionalGallerist = galleristRepository.findById(dtoSaledCarIU.getGalleristId());
        Optional<Car> optionalCar = carRepository.findById(dtoSaledCarIU.getCarId());
        Optional<Customer> optionalCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optionalCustomer.isPresent() && optionalGallerist.isPresent() && optionalCar.isPresent()) {
            SaledCar saledCar = new SaledCar();
            saledCar.setGallerist(optionalGallerist.get());
            saledCar.setCar(optionalCar.get());
            saledCar.setCustomer(optionalCustomer.get());
            saledCar.setCreateTime(new Date());
            return saledCar;
        }
        throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, ""));
    }

    public boolean isCarStatusSalable(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, optionalCar.get().toString()));
        }
        return optionalCar.get().getCarStatusType().equals(CarStatusType.SALABLE);
    }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car) {

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal customerRemainingUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(), DateUtils.getCurrentDate());
        BigDecimal bigDecimalUSD = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        return customerRemainingUSDAmount.multiply(bigDecimalUSD);
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
        if (!isCarStatusSalable(dtoSaledCarIU.getCarId())) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_IS_NOT_SALABLE,dtoSaledCarIU.getCarId().toString()));
        }
        if (!checkAmount(dtoSaledCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH," "));
        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
        Car car =savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer,car));
        customerRepository.save(customer);

        return toDto(savedSaledCar);
    }

    public DtoSaledCar toDto(SaledCar saledCar) {
        DtoSaledCar dtoSaledCar = new DtoSaledCar();


        DtoCustomer dtoCustomer = new DtoCustomer();
            DtoAccount dtoAccount = new DtoAccount();
            DtoAddress dtoAddress = new DtoAddress();

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(saledCar.getCustomer(),dtoCustomer);
            BeanUtils.copyProperties(saledCar.getCustomer().getAccount(),dtoAccount);
            BeanUtils.copyProperties(saledCar.getCustomer().getAddress(),dtoAddress);
        dtoCustomer.setDtoAccount(dtoAccount);
        dtoCustomer.setDtoAddress(dtoAddress);

        BeanUtils.copyProperties(saledCar.getGallerist(),dtoGallerist);
        dtoGallerist.setDtoAddress(dtoAddress);

        BeanUtils.copyProperties(saledCar.getCar(),dtoCar);


        BeanUtils.copyProperties(saledCar,dtoSaledCar);
        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setCar(dtoCar);

        return dtoSaledCar;
    }

}
