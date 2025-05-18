package titanic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.jupiter.api.Assertions;
import titanic.config.AppConfig;
import titanic.datamodel.Passenger;
import titanic.services.repository.PassengerRepository;

public class TestJPA2 {
    public static void main(String[] args) {
        // Load the Spring context
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the PassengerRepository bean
        PassengerRepository passengerRepository = context.getBean(PassengerRepository.class);

        // Create a passenger object
        Passenger passenger = new Passenger("Allison, Mr Hudson Joshua Creighton", "1st", 30.0, "male", false);

        // Save the passenger to the database
        passengerRepository.savePassenger(passenger);

        // Retrieve the passenger by ID
        Passenger retrievedPassenger = passengerRepository.findPassengerById(passenger.getId());

        // Assertions to verify correctness
        Assertions.assertNotNull(retrievedPassenger, "Retrieved passenger should not be null");
        Assertions.assertEquals(passenger.getName(), retrievedPassenger.getName(), "Names should match");
        Assertions.assertEquals(passenger.getPclass(), retrievedPassenger.getPclass(), "Pclass should match");
        Assertions.assertEquals(passenger.getAge(), retrievedPassenger.getAge(), "Age should match");
        Assertions.assertEquals(passenger.getSex(), retrievedPassenger.getSex(), "Sex should match");
        Assertions.assertEquals(passenger.isSurvived(), retrievedPassenger.isSurvived(), "Survival status should match");

        // Print the retrieved passenger to verify visually
        System.out.println("Retrieved Passenger: " + retrievedPassenger);
    }
}