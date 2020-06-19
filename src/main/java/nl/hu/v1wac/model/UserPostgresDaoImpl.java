package nl.hu.v1wac.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDAO {
    Connection con = PostgresBaseDao.getConnection();

    @Override
    public String getUserRole(String username, String password) {
        String querytext = "select * FROM useraccount where username = ? AND password = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(querytext);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getString("role");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
