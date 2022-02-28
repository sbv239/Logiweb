package ru.shramko.logiweb.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shramko.logiweb.dao.entity.CityEntity;
import ru.shramko.logiweb.dao.entity.TruckEntity;
import ru.shramko.logiweb.service.CityService;
import ru.shramko.logiweb.service.TruckService;

import java.util.List;

@Controller
@RequestMapping("/trucks")
public class TruckController {

    private final TruckService truckService;
    private final CityService cityService;


    @Autowired
    public TruckController(TruckService truckService, CityService cityService) {
        this.truckService = truckService;
        this.cityService = cityService;
    }

    @ModelAttribute("truckList")
    public List<TruckEntity> getTrucks(){
        return truckService.getTruckDataList();
    }

    @ModelAttribute("cityList")
    public List<CityEntity> getCities(){
        return cityService.getCityList();
    }

    @GetMapping("/deleteTruck/{id}")
    public String deleteTrucks(@PathVariable("id") int id) {
        truckService.deleteTruck(id);
        return "redirect:/trucks/all";
    }
    @PostMapping(value = "/add")
    public String newTruck(TruckEntity truckEntity, BindingResult bindingResult, Model model) {
        truckService.addTruck(truckEntity);
        return "redirect:/trucks/all";
    }
    @GetMapping("/{id}/edit")
    public String editTruck(Model model, @PathVariable("id") int id) {
        model.addAttribute("truckEntity", truckService.getTruckData(id));
        return "/trucks/edit.html";
    }
    @PostMapping("/{id}")
    public String updateTruck(@ModelAttribute("truckEntity") TruckEntity truckEntity, @PathVariable("id") int id) {
        truckService.updateTruck(truckEntity);
        return "redirect:/trucks/all";
    }
    @GetMapping("/all")
    public String trucksPage(TruckEntity truckEntity) {
        return "/trucks/index.html";
    }
}
