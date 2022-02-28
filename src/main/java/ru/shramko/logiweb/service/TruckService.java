package ru.shramko.logiweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.entity.TruckEntity;
import ru.shramko.logiweb.dao.TruckRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class TruckService {

    private TruckRepository truckRepository;

    @Autowired
    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public List<TruckEntity> getTruckDataList() {
        return truckRepository.findAll();
    }

    public void deleteTruck(int id) {
        truckRepository.deleteById(id);
    }

    public void addTruck(TruckEntity truckEntity) {
        truckEntity.setState("TRUE");
        truckRepository.save(truckEntity);
    }

    public TruckEntity getTruckData(int id) {
        return truckRepository.getById(id);
    }

    public void updateTruck(TruckEntity truckEntity) {
        truckRepository.save(truckEntity);
    }
}
