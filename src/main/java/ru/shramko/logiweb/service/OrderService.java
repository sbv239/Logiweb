package ru.shramko.logiweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.CargoRepository;
import ru.shramko.logiweb.dao.OrderRepository;
import ru.shramko.logiweb.dao.PointRepository;
import ru.shramko.logiweb.dao.TruckRepository;
import ru.shramko.logiweb.dao.entity.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final PointRepository pointRepository;
    private final CargoRepository cargoRepository;
    private final TruckRepository truckRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, PointRepository pointRepository, CargoRepository cargoRepository, TruckRepository truckRepository) {
        this.orderRepository = orderRepository;
        this.pointRepository = pointRepository;
        this.cargoRepository = cargoRepository;
        this.truckRepository = truckRepository;
    }

    public void addOrder(Point point) {

        Order order = new Order();
        order.setNumber(generateNumber(order));

        Cargo cargo = point.getCargo();
        City endCity = point.getCity();

        order.setStartCity(cargo.getStartCity());
        order.setStatus("Выполняется");
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
    }

    private String generateNumber(Order order) {
        DateFormat df = new SimpleDateFormat("dd-MM-yy");
        return "" + df.format(new Date()) + "-" + (int)(1000 * Math.random());
    }

    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    public List<Truck> getTruckForOrder(int id) {
        Order order = orderRepository.getById(id);

        Double weight = 0.0;

        for (Point point : order.getPointList()) {
            if (point.getType().equals("погрузка")) {
                weight = point.getCargo().getWeight();
            }
        }

        return truckRepository.findAllByCityAndStateAndOrderIsNullAndCapacityIsGreaterThan(order.getStartCity(), "true", weight);
    }

    public Order getOrder(int id) {
        return orderRepository.getById(id);
    }

    public void setTruckToOrder(int orderId, int truckId) {
        Order order = orderRepository.getById(orderId);
        Truck truck = truckRepository.getById(truckId);
        order.setTruck(truck);
        truck.setOrder(order);
        System.out.println(truck);
        System.out.println(order);
        orderRepository.save(order);
        truckRepository.save(truck);
    }
}
