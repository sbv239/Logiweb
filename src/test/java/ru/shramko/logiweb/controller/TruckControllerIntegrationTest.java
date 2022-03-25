package ru.shramko.logiweb.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.shramko.logiweb.api.controller.TruckController;
import ru.shramko.logiweb.dao.entity.Truck;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TruckControllerIntegrationTest {

    @Autowired
    private TruckController truckController;

    @Test
    public void testNewTruck() {
        Truck truck = new Truck();

        truck.setState("true");
        truck.setShift(8);
        truck.setCapacity(50.0);
        truck.setReg("807-UB-0161");

        String outcome = truckController.newTruck(truck);

        assertThat(outcome, is(equalTo("redirect:/crm/trucks/")));
    }

    @Test
    public void testNewTruckWithoutParams() {
        Truck truck = new Truck();

        truck.setState("true");

        String outcome = truckController.newTruck(truck);

        assertThat(outcome, is(equalTo("redirect:/crm/trucks/?err=emptyfields")));
    }

}
