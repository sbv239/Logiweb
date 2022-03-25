package ru.shramko.logiweb.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ru.shramko.logiweb.dao.TruckRepository;
import ru.shramko.logiweb.dao.entity.City;
import ru.shramko.logiweb.dao.entity.Truck;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TruckRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TruckRepository truckRepository;

    @Test
    public void testFindAllByCityAndStateAndOrderIsNullAndCapacityIsGreaterThan() {
        Truck truck = new Truck();
        City city = new City("Samara", 8, 4);
        entityManager.persist(city);

        truck.setCapacity(50.0);
        truck.setShift(8);
        truck.setReg("525-UO-1795");
        truck.setState("true");
        truck.setCity(city);

        entityManager.persist(truck);

        List<Truck> truckList = truckRepository.findAllByCityAndStateAndOrderIsNullAndCapacityIsGreaterThan(city, "true", 15.0);

        assertThat(truckList, contains(truck));

    }
}
