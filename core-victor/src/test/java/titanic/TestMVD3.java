package titanic;

import titanic.services.PassengerService;
import titanic.datamodel.Passenger;
import titanic.utils.ConfigUtils;

import java.util.List;

public class TestMVD3 {
    public static void main(String[] args) {
        PassengerService passengerService = new PassengerService();

        String testFile = ConfigUtils.getProperty("titanicTestFile");

        // Load passengers from the CSV file
        List<Passenger> passengers = passengerService.loadPassengers(testFile);

        // Print each passenger
        for (Passenger passenger : passengers) {
            System.out.println(passenger);
        }
    }
}
