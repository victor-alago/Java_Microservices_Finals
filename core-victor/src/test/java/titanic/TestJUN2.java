package titanic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import titanic.datamodel.Passenger;
import titanic.services.PassengerService;
import titanic.utils.ConfigUtils;

import java.util.List;

public class TestJUN2 {

    @Test
    public void testLoadPassengersFromDirectory() {
        PassengerService passengerService = new PassengerService();
        String bothFiles = ConfigUtils.getProperty("bothFilesFolder");

        // Load passengers from all CSV files in the directory
        List<Passenger> passengers = passengerService.loadPassengersFromDirectory(bothFiles);

        // Assert the total number of passengers
        Assertions.assertEquals(1313, passengers.size(), "Total passenger count across files does not match expected value!");
    }
}