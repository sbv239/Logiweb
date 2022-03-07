package ru.shramko.logiweb.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shramko.logiweb.dao.entity.City;
import ru.shramko.logiweb.api.model.Driver;
import ru.shramko.logiweb.service.CityService;
import ru.shramko.logiweb.service.DriverService;

import java.util.List;

@Controller
@RequestMapping("/crm/drivers")
public class DriverController {

    private DriverService driverService;
    private CityService cityService;

    @Autowired
    public DriverController(DriverService driverService, CityService cityService) {
        this.driverService = driverService;
        this.cityService = cityService;
    }

    @ModelAttribute("cityList")
    public List<City> getCities(){
        return cityService.getCityList();
    }

    @ModelAttribute("driverList")
    public List<Driver> getDrivers(){
        return driverService.getDriverDataList();
    }

    @GetMapping("/")
    public String driversMainPage(Driver driver) {
        return "/drivers/index.html";
    }

    @PostMapping(value = "/add")
    public String newTruck(Driver driver) {
        driverService.addDriver(driver);
        return "redirect:/crm/drivers/";
    }
}
