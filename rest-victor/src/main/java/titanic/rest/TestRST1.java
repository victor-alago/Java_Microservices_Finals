package titanic.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "titanic.rest") // Only scan components in titanic.rest
public class TestRST1 {
    public static void main(String[] args) {
        SpringApplication.run(TestRST1.class, args);
    }
}