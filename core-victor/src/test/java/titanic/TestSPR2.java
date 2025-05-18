package titanic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import titanic.config.AppConfig;
import titanic.datamodel.Passenger;
import titanic.services.PassengerService;
import titanic.services.dao.PassengerDAO;
import titanic.utils.ConfigUtils;

import java.util.List;

public class TestSPR2 {
    public static void main(String[] args) {
        // Load Spring context
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the PassengerService and PassengerDAO beans
        PassengerService passengerService = context.getBean(PassengerService.class);
        PassengerDAO passengerDAO = context.getBean(PassengerDAO.class);

        // Create the table
        passengerDAO.createTable();

        // Load passengers from CSV
        String testFile = ConfigUtils.getProperty("titanicTestFile");
        List<Passenger> passengers = passengerService.loadPassengers(testFile);

        // Save passengers to the database
        passengerDAO.savePassengers(passengers);

        // Print passengers from the database
        passengerDAO.printPassengers();
    }
}