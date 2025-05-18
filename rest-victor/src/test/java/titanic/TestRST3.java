package titanic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import titanic.rest.TestRST1;
import titanic.rest.model.Passenger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TestRST1.class)
public class TestRST3 {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testPostAndGet() {
        // POST: Add a new passenger
        String postUrl = "http://localhost:8080/api/passengers";
        Passenger passenger = new Passenger();
        passenger.setName("Allen, Miss Elisabeth Walton");
        passenger.setPclass("1st");
        passenger.setAge(29);
        passenger.setSex("female");
        passenger.setSurvived(true);

        System.out.println("---- START: Posting a New Passenger ----");
        restTemplate.postForEntity(postUrl, passenger, String.class);
        System.out.println("POST Complete: Passenger added successfully.\n");

        // GET: Retrieve all passengers
        String getUrl = "http://localhost:8080/api/passengers";
        System.out.println("---- START: Getting All Passengers ----");
        Passenger[] passengers = restTemplate.getForObject(getUrl, Passenger[].class);

        // Print each passenger
        if (passengers != null && passengers.length > 0) {
            System.out.println("GET Result: Retrieved Passengers:");
            for (Passenger p : passengers) {
                System.out.println(p);
            }
        } else {
            System.out.println("GET Result: No passengers found.");
        }
        System.out.println("---- END: Getting All Passengers ----\n");
    }
}