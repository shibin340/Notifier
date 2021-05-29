package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/notifier_app?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
 
    private static Connection connection = null;
 
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Loading The Driver Class
                Class.forName(DRIVER);
 
                // Getting the connection Object
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.print("connected:");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
 
        return connection;
    }
}
