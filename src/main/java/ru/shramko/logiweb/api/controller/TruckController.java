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

    private static final String REDIRECT_TO_TRUCKLIST = "redirect:/crm/trucks/";


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
        return REDIRECT_TO_TRUCKLIST;
    }
    @PostMapping(value = "/add")
  
    public String newTruck(Truck truck) {
        String result = truckService.addTruck(truck);
        if (result.equals("EMPTY FIELDS")) {
            return REDIRECT_TO_TRUCKLIST + "?err=emptyfields";
        }
        return REDIRECT_TO_TRUCKLIST;
    }
    @GetMapping("/{id}/edit")
    public String editTruck(Model model, @PathVariable("id") int id) {
        model.addAttribute("truck", truckService.getTruckData(id));
        return "/trucks/edit.html";
    }
    @PostMapping("/{id}")
    public String updateTruck(@ModelAttribute("truck") Truck truck, @PathVariable("id") int id) {
        truckService.updateTruck(truck);
        return REDIRECT_TO_TRUCKLIST;
    }
    @GetMapping("/")
    public String trucksPage(Model model, Truck truck) {
        model.addAttribute("truckList", truckService.getTruckDataList());
        return "/trucks/index.html";
    }
}