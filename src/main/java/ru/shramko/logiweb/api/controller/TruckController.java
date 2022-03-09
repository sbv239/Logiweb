package ru.shramko.logiweb.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shramko.logiweb.dao.entity.City;
import ru.shramko.logiweb.dao.entity.Truck;
import ru.shramko.logiweb.service.CityService;
import ru.shramko.logiweb.service.TruckService;

import java.util.List;

@Controller
@RequestMapping("/crm/trucks")
public class TruckController {

    private final TruckService truckService;
    private final CityService cityService;


    @Autowired
    public TruckController(TruckService truckService, CityService cityService) {
        this.truckService = truckService;
        this.cityService = cityService;
    }

    @ModelAttribute("cityList")
    public List<City> getCities(){
        return cityService.getCityList();
    }

    @GetMapping("/deleteTruck/{id}")
    public String deleteTrucks(@PathVariable("id") int id) {
        truckService.deleteTruck(id);
        return "redirect:/crm/trucks/";
    }
    @PostMapping(value = "/add")
    public String newTruck(Truck truck, BindingResult bindingResult, Model model) {
        truckService.addTruck(truck);
        return "redirect:/crm/trucks/";
    }
    @GetMapping("/{id}/edit")
    public String editTruck(Model model, @PathVariable("id") int id) {
        model.addAttribute("truck", truckService.getTruckData(id));
        return "/trucks/edit.html";
    }
    @PostMapping("/{id}")
    public String updateTruck(@ModelAttribute("truck") Truck truck, @PathVariable("id") int id) {
        truckService.updateTruck(truck);
        return "redirect:/crm/trucks/";
    }
    @GetMapping("/")
    public String trucksPage(Model model, Truck truck) {
        model.addAttribute("truckList", truckService.getTruckDataList());
        return "/trucks/index.html";
    }
}
