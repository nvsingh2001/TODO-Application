import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static String url = "jdbc:sqlite:/home/ares/IdeaProjects/Todo_Application/src/TasksDB.sqlite";

    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
