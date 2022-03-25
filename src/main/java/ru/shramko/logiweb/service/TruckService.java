package ru.shramko.logiweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.entity.Truck;
import ru.shramko.logiweb.dao.TruckRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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

    @Transactional
    public void deleteTruck(int id) {
        truckRepository.deleteById(id);
    }

    @Transactional
    public String addTruck(Truck truck) {
        if(Objects.equals(truck.getReg(), "") || truck.getShift() == null || truck.getCapacity() == null) {
            log.info("New truck was NOT added (empty fields)");
            return "EMPTY FIELDS";
        } else {
            truck.setState("true");
            truckRepository.save(truck);
            log.info("New truck was added: " + truck);
            return "OK";
        }
    }

    public Truck getTruckData(int id) {
        return truckRepository.getById(id);
    }

    @Transactional
    public void updateTruck(Truck truck) {
        truckRepository.save(truck);
        log.info("Truck was updated: " + truck);
    }
}
