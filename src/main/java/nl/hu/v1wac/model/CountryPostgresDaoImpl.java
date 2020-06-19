package nl.hu.v1wac.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryPostgresDaoImpl extends PostgresBaseDao implements CountryDAO  {
    Connection con = PostgresBaseDao.getConnection();

    public boolean save(Country country) {
        String querytext = "INSERT into country values(?,?,?,?,?,?,null,?,null,null,null,?,?,null,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(querytext);
            pstmt.setString(1, country.getCode());
            pstmt.setString(2, country.getIso3());
            pstmt.setString(3, country.getName());
            pstmt.setString(4, country.getContinent());
            pstmt.setString(5, country.getRegion());
            pstmt.setDouble(6, country.getSurface());
            pstmt.setInt(7, country.getPopulation());
            pstmt.setString(9, country.getGovernment());
            pstmt.setString(8, country.getName());
            pstmt.setDouble(10, country.getLatitude());
            pstmt.setDouble(11, country.getLongitude());
            pstmt.setString(12, country.getCapital());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        String querytext = "select * FROM country";
        Statement pstmt = null;
        try {
            pstmt = con.createStatement();

        ResultSet rs = pstmt.executeQuery(querytext);
        while (rs.next()) {
            Country g = new Country(rs.getString("code"), rs.getString("iso3"), rs.getString("name"), rs.getString("capital"), rs.getString("continent"),
                    rs.getString("region"), rs.getDouble("surfacearea"), rs.getInt("population"), rs.getString("governmentform"), rs.getDouble("latitude"), rs.getDouble("longitude"));
            countries.add(g);
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return countries;
    }

    public Country findByCode(String code) {
        String querytext = "select * FROM country where code = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(querytext);

            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Country country = new Country(rs.getString("code"), rs.getString("iso3"), rs.getString("name"), rs.getString("capital"), rs.getString("continent"),
                        rs.getString("region"), rs.getDouble("surfacearea"), rs.getInt("population"), rs.getString("governmentform"), rs.getDouble("latitude"), rs.getDouble("longitude"));
                return country;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Country> find10largestPopulations()  {
        List<Country> countries = new ArrayList<>();
        String querytext = "select * FROM country ORDER BY population DESC";
        try {
        Statement pstmt = con.createStatement();
        ResultSet rs = pstmt.executeQuery(querytext);
        while (rs.next()) {
            Country g = null;

                g = new Country(rs.getString("code"), rs.getString("iso3"), rs.getString("name"), rs.getString("capital"), rs.getString("continent"),
                        rs.getString("region"), rs.getDouble("surfacearea"), rs.getInt("population"), rs.getString("governmentform"), rs.getDouble("latitude"), rs.getDouble("longitude"));

            countries.add(g);
            if (countries.size() == 10) {
                return countries;
            }
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return countries;
    }

    public List<Country> find10largestSurfaces()  {
        List<Country> countries = new ArrayList<>();
        String querytext = "select * FROM country ORDER BY surfacearea DESC";
        try {
        Statement pstmt = con.createStatement();
        ResultSet rs = pstmt.executeQuery(querytext);
        while (rs.next()) {
            Country g = null;

                g = new Country(rs.getString("code"), rs.getString("iso3"), rs.getString("name"), rs.getString("capital"), rs.getString("continent"),
                        rs.getString("region"), rs.getDouble("surfacearea"), rs.getInt("population"), rs.getString("governmentform"), rs.getDouble("latitude"), rs.getDouble("longitude"));

            countries.add(g);
            if (countries.size() == 10) {
                return countries;
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public boolean update(Country country) {
        String querytext = "UPDATE country SET iso3 = ?, name  = ?, continent = ?, region = ?, surfacearea = ?, population=?, governmentform=?, latitude= ?, longitude=?, capital = ? where code=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(querytext);
            pstmt.setString(11, country.getCode());
            pstmt.setString(1, country.getIso3());
            pstmt.setString(2, country.getName());
            pstmt.setString(3, country.getContinent());
            pstmt.setString(4, country.getRegion());
            pstmt.setDouble(5, country.getSurface());
            pstmt.setInt(6, country.getPopulation());
            pstmt.setString(7, country.getGovernment());
            pstmt.setDouble(8, country.getLatitude());
            pstmt.setDouble(9, country.getLongitude());
            pstmt.setString(10, country.getCapital());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Country country) {
        String querytext = "DELETE FROM country where code = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(querytext);

            pstmt.setString(1, country.getCode());
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
