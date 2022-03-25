package ru.shramko.logiweb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.shramko.logiweb.dao.entity.Truck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TruckServiceIntegrationTest {

    @Autowired
    private TruckService truckService;

    @Test
    public void testAddTruck() {
        Truck truck = new Truck();

        truck.setCapacity(50.0);
        truck.setShift(8);
        truck.setReg("525-UO-1795");

        truckService.addTruck(truck);

        assertNotNull(truck);
        assertNotNull(truck.getId());
        assertEquals("true", truck.getState());
    }

}
