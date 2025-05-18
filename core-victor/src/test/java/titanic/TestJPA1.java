package titanic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import titanic.config.AppConfig;
import titanic.datamodel.Passenger;

public class TestJPA1 {
    public static void main(String[] args) {
        // Load Spring context
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the EntityManagerFactory bean
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();

        // Persist a Passenger
        Passenger passenger = new Passenger("Allen, Miss Elisabeth Walton", "1st", 29.0, "female", true);

        em.getTransaction().begin();
        em.persist(passenger);
        em.getTransaction().commit();

        // Verify insertion by retrieving the passenger
        Passenger retrievedPassenger = em.find(Passenger.class, passenger.getId());
        System.out.println("Retrieved Passenger: " + retrievedPassenger);

        // Close the EntityManager
        em.close();
    }
}