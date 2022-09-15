
package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBconnection {
    public static Connection createConnection() {
        Connection con = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
            System.out.println("Connected Successfully");

        } catch (Exception e) {
            System.out.println("DB error is " + e.getMessage());
        }
        return con;

    }
}
