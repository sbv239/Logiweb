package ru.shramko.logiweb.service;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DriverService {

    private DriverRepository driverRepository;
    private OrderRepository orderRepository;
    private TruckRepository truckRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, OrderRepository orderRepository, TruckRepository truckRepository) {
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.truckRepository = truckRepository;
    }

    public void addDriver(Driver driver) {
        driver.setHours(0);
        driverRepository.save(driver);
        log.info("New driver was added: " + driver);
    }

    public List<Driver> getDriverDataList() {
       return driverRepository.findAll();
    }

    public List<Driver> getDriversForOrder(int id) {

        Order order = orderRepository.getById(id);
        Truck truck = truckRepository.getById(order.getTruck().getId());
        City startCity = order.getStartCity();
        City endCity = order.getEndCity();

        int orderTimeToComplete = 12 * Math.abs(startCity.getXCoord() - endCity.getXCoord() + startCity.getYCoord() - endCity.getYCoord());
        log.info("Часов для выполнения заказа № " + order.getNumber() + ": " + orderTimeToComplete);

        int workingHoursInCurrentMonth = truck.getShift() * Utils.daysLeft();
        log.info("Для фуры, назначенной на заказ, осталось рабочих часов (с учетом смены " + truck.getShift() + ") в месяц: " + workingHoursInCurrentMonth);

        List<Driver> driverList = driverRepository.findAllByCityAndStatus(order.getStartCity(), "Отдых").stream()
                .filter(driver -> (176 - driver.getHours()) > Math.min(orderTimeToComplete, workingHoursInCurrentMonth))
                .collect(Collectors.toList());

        log.info("Найдено водителей: " + driverList.size());

        return driverList;
    }
}
