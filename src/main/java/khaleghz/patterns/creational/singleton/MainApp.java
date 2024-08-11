package khaleghz.patterns.creational.singleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class MainApp {


    public static void main(String[] args) {

        var dbConnection1 = DBConnection.getInstance();
        var dbConnection2 = DBConnection.getInstance();
        assert Objects.equals(dbConnection1, dbConnection2);

        var connection = DBConnection.getInstance().getConnection();

        var createTableQuery = "CREATE TABLE person(id INT PRIMARY KEY, name VARCHAR)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        var insertQuery = "INSERT INTO person(id, name) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {

            ps.setInt(1, 110);
            ps.setString(2, "Ali Khaleghzadegan");
            ps.execute();

            ps.setInt(1, 111);
            ps.setString(2, "Neda Rafieiolhossini");
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        var selectQuery = "SELECT COUNT(*) FROM PERSON";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(selectQuery)) {

                if (rs.next()) {
                    assert rs.getInt(1) == 2;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
