package ru.shramko.logiweb.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shramko.logiweb.dao.TruckRepository;
import ru.shramko.logiweb.dao.entity.City;
import ru.shramko.logiweb.dao.entity.Truck;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TruckServiceUnitTest {

    @InjectMocks
    private TruckService truckService;

    @Mock
    private TruckRepository truckRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTruck() {

        Truck truck = new Truck();

        truck.setCapacity(50.0);
        truck.setShift(8);
        truck.setReg("525-UO-1795");


        truckService.addTruck(truck);

        assertEquals("true", truck.getState());
    }
}
