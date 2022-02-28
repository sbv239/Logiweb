package ru.shramko.logiweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.DriverRepository;
import ru.shramko.logiweb.model.Driver;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void addDriver(Driver driver) {
        driver.setHours(0);
        driverRepository.save(DriverMapper.toEntity(driver));
    }

    public List<Driver> getDriverDataList() {
        return DriverMapper.toList(driverRepository.findAll());
    }
}
