package titanic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import titanic.config.AppConfig;

public class TestSPR1 {
    public static void main(String[] args) {
        // Load Spring context
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the bean
        String message = context.getBean(String.class);

        // Print the message
        System.out.println(message);
    }
}