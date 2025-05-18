package titanic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.client.RestTemplate;
import titanic.rest.model.Passenger;

@SpringBootApplication(scanBasePackages = "titanic.rest")
@EntityScan(basePackages = "titanic.rest.model")
public class TestRST2 {

    public static void main(String[] args) {
        // Start the Spring Boot application
        SpringApplication.run(TestRST2.class, args);

        // RestTemplate for HTTP calls
        RestTemplate restTemplate = new RestTemplate();

        // Define the base URL for the API
        String baseUrl = "http://localhost:8080/api/passengers";

        // 1. Create a new passenger (POST request)
        Passenger passenger = new Passenger();
        passenger.setName("Allen, Miss Elisabeth Walton");
        passenger.setPclass("1st");
        passenger.setAge(29.0);
        passenger.setSex("female");
        passenger.setSurvived(true);

        System.out.println("Making POST request to add a passenger...");
        String postResponse = restTemplate.postForObject(baseUrl, passenger, String.class);
        System.out.println("POST Response: " + postResponse);

        // 2. Get all passengers (GET request)
        System.out.println("\nMaking GET request to retrieve all passengers...");
        Passenger[] passengers = restTemplate.getForObject(baseUrl, Passenger[].class);

        // Print the list of passengers
        System.out.println("Passengers retrieved:");
        for (Passenger p : passengers) {
            System.out.println(p);
        }
    }
}