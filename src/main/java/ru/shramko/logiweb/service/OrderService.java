package ru.shramko.logiweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.*;
import ru.shramko.logiweb.dao.entity.*;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final PointRepository pointRepository;
    private final CargoRepository cargoRepository;
    private final TruckRepository truckRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, PointRepository pointRepository,
                        CargoRepository cargoRepository, TruckRepository truckRepository, DriverRepository driverRepository) {
        this.orderRepository = orderRepository;
        this.pointRepository = pointRepository;
        this.cargoRepository = cargoRepository;
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
    }
    @Transactional
    public void addOrder(Point point) {
        Cargo cargo = point.getCargo();
        City endCity = point.getCity();

        Order order = new Order();
        order.setNumber(generateNumber());

        order.setStartCity(cargo.getStartCity());
        order.setEndCity(endCity);
        orderRepository.save(order);

        Point endPoint = new Point(cargo, "выгрузка", order, endCity);
        pointRepository.save(endPoint);

        point.setCity(cargo.getStartCity());
        point.setOrder(order);
        point.setType("погрузка");
        pointRepository.save(point);

        cargo.setStatus("Отгружен");
        cargo.setEndCity(endCity);
        cargoRepository.save(cargo);

        log.info("Создан заказ №" + order.getNumber() + ": Начальная точка: "
                + order.getStartCity() + ", конечная точка: " + order.getEndCity()
                + ", суммарный груз: " + cargo.getWeight());
    }

    private String generateNumber() {
        int suffix = 0;
        try {
            suffix = SecureRandom.getInstanceStrong().nextInt(1000);
        } catch (NoSuchAlgorithmException e) {
            log.error("Не получилось сгенерирваоть уникальный номер для заказа");
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yy");
        return "" + df.format(new Date()) + "-" + suffix;
    }

    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    public List<Truck> getTrucksForOrder(int id) {
        Order order = orderRepository.getById(id);

        Double weight = 0.0;

        for (Point point : order.getPointList()) {
            if (point.getType().equals("погрузка")) {
                weight = point.getCargo().getWeight();
            }
        }

        return truckRepository
                .findAllByCityAndStateAndOrderIsNullAndCapacityIsGreaterThan(order.getStartCity(), "true", weight);
    }

    public Order getOrder(int id) {
        return orderRepository.getById(id);
    }

    @Transactional
    public void setTruckToOrder(int orderId, int truckId) {
        Order order = orderRepository.getById(orderId);
        Truck truck = truckRepository.getById(truckId);
        order.setTruck(truck);
        truck.setOrder(order);
        orderRepository.save(order);
        truckRepository.save(truck);
    }
    @Transactional
    public void setDriverToOrder(int orderId, int driverId) {
        Order order = orderRepository.getById(orderId);
        Driver driver = driverRepository.getById(driverId);
        Truck truck = truckRepository.getById(order.getTruck().getId());
        driver.setOrder(order);
        driver.setTruck(truck);
        driver.setStatus("В смене");
        driverRepository.save(driver);
    }
}
