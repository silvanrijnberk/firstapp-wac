package nl.hu.v1wac.model;

import java.util.List;

public interface CountryDAO {
    public boolean save(Country country);
    public List<Country> findAll();
    public Country findByCode(String code);
    public List<Country> find10largestPopulations();
    public List<Country> find10largestSurfaces();
    public boolean update(Country country);
    public boolean delete(Country country);
}
