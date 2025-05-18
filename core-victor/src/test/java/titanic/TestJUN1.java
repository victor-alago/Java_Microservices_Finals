package titanic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import titanic.datamodel.Passenger;
import titanic.services.PassengerService;
import titanic.utils.ConfigUtils;

import java.util.List;

public class TestJUN1 {

    @Test
    public void testPassengerCount() {

        PassengerService passengerService = new PassengerService();
        String testFile = ConfigUtils.getProperty("titanicTestFile");
        // Load passengers from the CSV file
        List<Passenger> passengers = passengerService.loadPassengers(testFile);

        // Assert the number of passengers
        Assertions.assertEquals(88, passengers.size(), "Passenger count does not match expected value!");
    }
}