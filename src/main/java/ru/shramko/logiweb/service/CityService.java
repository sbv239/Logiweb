package ru.shramko.logiweb.service;

import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.CityRepository;
import ru.shramko.logiweb.dao.entity.CityEntity;
import ru.shramko.logiweb.dao.entity.TruckEntity;

import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityEntity> getCityList() {
        return cityRepository.findAll();
    }
}

