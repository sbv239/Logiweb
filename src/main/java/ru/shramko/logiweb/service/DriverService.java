package ru.shramko.logiweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.CityRepository;
import ru.shramko.logiweb.dao.DriverRepository;
import ru.shramko.logiweb.dao.OrderRepository;
import ru.shramko.logiweb.dao.TruckRepository;
import ru.shramko.logiweb.dao.entity.City;
import ru.shramko.logiweb.dao.entity.Driver;
import ru.shramko.logiweb.dao.entity.Order;
import ru.shramko.logiweb.dao.entity.Truck;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private DriverRepository driverRepository;
    private OrderRepository orderRepository;
    private CityRepository cityRepository;
    private TruckRepository truckRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, OrderRepository orderRepository, CityRepository cityRepository, TruckRepository truckRepository) {
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.cityRepository = cityRepository;
        this.truckRepository = truckRepository;
    }

    public void addDriver(Driver driver) {
        driver.setHours(0);
        driverRepository.save(driver);
    }

    public List<Driver> getDriverDataList() {
        return driverRepository.findAll();
    }

    public List<Driver> getDriverForOrder(int id) {

        Order order = orderRepository.getById(id);
        Truck truck = truckRepository.getById(order.getTruck().getId());
        City startCity = cityRepository.getById(order.getStartCity().getId());
        City endCity = cityRepository.getById(order.getEndCity().getId());

        int workingHoursInCurrentMonth = truck.getShift() * Utils.daysLeft();
        int orderTimeToComplete = 12 * Math.abs(startCity.getXCoord() - endCity.getXCoord() + startCity.getYCoord() - endCity.getYCoord());


        return driverRepository.findAllByCityAndStatus(order.getStartCity(), "Отдых").stream()
                .filter(driver -> (176 - driver.getHours()) > Math.min(orderTimeToComplete, workingHoursInCurrentMonth))
                .collect(Collectors.toList());
    }
}
