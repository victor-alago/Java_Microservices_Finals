package titanic.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import titanic.rest.model.Passenger;
import titanic.rest.repository.PassengerRepository;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerRepository passengerRepository;

    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @PostMapping
    public ResponseEntity<String> createPassenger(@RequestBody Passenger passenger) {
        passengerRepository.savePassenger(passenger);
        return ResponseEntity.ok("Passenger added successfully: " + passenger.getName());
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }
}