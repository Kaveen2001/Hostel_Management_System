package lk.ijse.hostel.util;

import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

 // Singleton class
 // This class is created to configure a SessionFactory
public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;


    private FactoryConfiguration() throws IOException {
        //load that hibernate.properties file to a util Properties reference
        /*Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        configuration.setProperties(properties);*/

        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));

        // Then, configure that util Properties file to SessionFactory
        Configuration configuration = new Configuration().mergeProperties(properties)
        .addAnnotatedClass(Student.class)
        .addAnnotatedClass(Room.class)
        .addAnnotatedClass(Reservation.class)
        .addAnnotatedClass(User.class);

        // Build a SessionFactory and assign it to SessionFactory Reference
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        // Get a New Session from SessionFactory and will return it
        return sessionFactory.openSession();
    }
}
