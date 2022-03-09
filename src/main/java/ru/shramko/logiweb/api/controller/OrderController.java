package ru.shramko.logiweb.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shramko.logiweb.dao.entity.Cargo;
import ru.shramko.logiweb.dao.entity.City;
import ru.shramko.logiweb.dao.entity.Order;
import ru.shramko.logiweb.dao.entity.Point;
import ru.shramko.logiweb.service.CityService;
import ru.shramko.logiweb.service.CargoService;
import ru.shramko.logiweb.service.DriverService;
import ru.shramko.logiweb.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/crm/orders")
@Slf4j
public class OrderController {

    private final CityService cityService;
    private final CargoService cargoService;
    private final OrderService orderService;
    private final DriverService driverService;

    @Autowired
    public OrderController(CityService cityService, CargoService cargoService, OrderService orderService, DriverService driverService) {
        this.cityService = cityService;
        this.cargoService = cargoService;
        this.orderService = orderService;
        this.driverService = driverService;
    }

    @ModelAttribute("cityList")
    public List<City> getCities(){
        return cityService.getCityList();
    }

    @ModelAttribute("cargoList")
    public List<Cargo> getCargos(){
        return cargoService.getCargoList();
    }

    @ModelAttribute("orderList")
    public List<Order> getOrders(){
        return orderService.getOrderList();
    }

    @GetMapping("/")
    public String ordersMainPage(Point point) {
        return "/orders/index.html";
    }

    @PostMapping(value = "/add")
    public String newTruck(Point point) {
        orderService.addOrder(point);
        log.info("Создан заказ на основе: " + point);
        return "redirect:/crm/orders/";
    }
    @GetMapping("/settruck/{id}")
    public String setTruck(Model model, @PathVariable("id") int id) {
        model.addAttribute("truckList", orderService.getTruckForOrder(id));
        model.addAttribute("order", orderService.getOrder(id));
        return "/orders/valid_trucks.html";
    }

    @GetMapping("/settruck/{orderid}/{truckid}")
    public String setTruckToOrder(@PathVariable("orderid") int orderId, @PathVariable("truckid") int truckId) {
        orderService.setTruckToOrder(orderId, truckId);
        return "redirect:/crm/orders/";
    }

    @GetMapping("/setdriver/{id}")
    public String setDriver(Model model, @PathVariable("id") int id) {
        model.addAttribute("driverList", driverService.getDriverForOrder(id));
        model.addAttribute("order", orderService.getOrder(id));
        return "/orders/valid_drivers.html";
    }

    @GetMapping("/setdriver/{orderid}/{driverid}")
    public String setDriverToOrder(@PathVariable("orderid") int orderId, @PathVariable("driverid") int driverId) {
        orderService.setDriverToOrder(orderId, driverId);
        return "redirect:/crm/orders/";
    }
}
