package nl.hu.v1wac.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresBaseDao {

    protected static Connection getConnection(){
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/worlddb",
                            "postgres", "fd7ae2GD5T10!");
            System.out.println("Opened database successfully");
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
            return c;
        }
    }
}
