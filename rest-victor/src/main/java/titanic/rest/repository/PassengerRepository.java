package titanic.rest.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import titanic.rest.model.Passenger;

import java.util.List;

@Repository
@Transactional
public class PassengerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void savePassenger(Passenger passenger) {
        entityManager.persist(passenger);
    }

    public List<Passenger> getAllPassengers() {
        return entityManager.createQuery("SELECT p FROM Passenger p", Passenger.class).getResultList();
    }
}
