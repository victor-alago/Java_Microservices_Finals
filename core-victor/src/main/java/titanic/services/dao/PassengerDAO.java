package titanic.services.dao;


import org.springframework.stereotype.Repository;
import titanic.datamodel.Passenger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PassengerDAO {

    private final DataSource dataSource;

    public PassengerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTable() {
        String sql = "CREATE TABLE passengers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "pclass VARCHAR(10), " +
                "age DOUBLE, " +
                "sex VARCHAR(10), " +
                "survived BOOLEAN)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePassengers(List<Passenger> passengers) {
        String sql = "INSERT INTO passengers (name, pclass, age, sex, survived) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Passenger passenger : passengers) {
                statement.setString(1, passenger.getName());
                statement.setString(2, passenger.getPclass());
                statement.setDouble(3, passenger.getAge());
                statement.setString(4, passenger.getSex());
                statement.setBoolean(5, passenger.isSurvived());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printPassengers() {
        String sql = "SELECT * FROM passengers";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("Passenger{id=" + resultSet.getInt("id") +
                        ", name='" + resultSet.getString("name") + '\'' +
                        ", pclass='" + resultSet.getString("pclass") + '\'' +
                        ", age=" + resultSet.getDouble("age") +
                        ", sex='" + resultSet.getString("sex") + '\'' +
                        ", survived=" + resultSet.getBoolean("survived") +
                        '}');
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}