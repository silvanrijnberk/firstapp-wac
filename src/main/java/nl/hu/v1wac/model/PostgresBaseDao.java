package nl.hu.v1wac.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresBaseDao {

    protected static Connection getConnection(){
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://ec2-54-75-246-118.eu-west-1.compute.amazonaws.com:5432/d7eu92m2ak80ff",
                            "zqmwvqjjrsvadu", "d971189352b079cb7ba71356a03b4b41be3fe77e06b5512f13669e995c18e440");
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
