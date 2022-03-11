package ru.shramko.logiweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.entity.Truck;
import ru.shramko.logiweb.dao.TruckRepository;

import java.util.List;

@Service
@Slf4j
public class TruckService {

    private TruckRepository truckRepository;

    @Autowired
    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public List<Truck> getTruckDataList() {
        return truckRepository.findAll();
    }

    public void deleteTruck(int id) {
        truckRepository.deleteById(id);
    }

    public void addTruck(Truck truck) {
        truck.setState("true");
        truckRepository.save(truck);
        log.info("New truck was added: " + truck);
    }

    public Truck getTruckData(int id) {
        return truckRepository.getById(id);
    }

    public void updateTruck(Truck truck) {
        truckRepository.save(truck);
        log.info("Truck was updated: " + truck);
    }
}
