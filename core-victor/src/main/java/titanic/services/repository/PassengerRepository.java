package titanic.services.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import titanic.datamodel.Passenger;

@Repository
@Transactional
public class PassengerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void savePassenger(Passenger passenger) {
        entityManager.persist(passenger);
    }

    public Passenger findPassengerById(Long id) {
        return entityManager.find(Passenger.class, id);
    }
}