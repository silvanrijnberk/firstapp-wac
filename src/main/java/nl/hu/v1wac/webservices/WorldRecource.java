package nl.hu.v1wac.webservices;

import nl.hu.v1wac.model.*;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.xml.ws.Response;

@DeclareRoles({"user", "guest"})
@Path("countries")
public class WorldRecource{

    private JsonObject convertToJson(Country country) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("code", country.getCode());
        jsonObjectBuilder.add("iso3", country.getIso3());
        jsonObjectBuilder.add("name", country.getName());
        jsonObjectBuilder.add("capital", country.getCapital());
        jsonObjectBuilder.add("continent", country.getContinent());
        jsonObjectBuilder.add("region", country.getRegion());
        jsonObjectBuilder.add("surface", country.getSurface());
        jsonObjectBuilder.add("population", country.getPopulation());
        jsonObjectBuilder.add("government", country.getGovernment());
        jsonObjectBuilder.add("latitude", country.getLatitude());
        jsonObjectBuilder.add("longitude", country.getLongitude());

        return jsonObjectBuilder.build();
    }


    @GET
    @Path("/")
    public String listCountries() {
        WorldService worldService = ServiceProvider.getWorldService();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Country country : worldService.getAllCountries()) {
            JsonObject countryJson = convertToJson(country);
            jsonArrayBuilder.add(countryJson);
        }

        JsonArray array = jsonArrayBuilder.build();

        return array.toString();
    }

    @GET
    @Path("/{code}")
    public String getCountryById(@PathParam("code") String code) {
        WorldService worldService = ServiceProvider.getWorldService();

        Country country = worldService.getCountryByCode(code);
        JsonObject jsonCountry = convertToJson(country);

        return jsonCountry.toString();
    }

    @GET
    @Path("/largestsurfaces")
    public String getLargestSurfaces() {
        WorldService ws = ServiceProvider.getWorldService();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Country c : ws.get10LargestSurfaces()) {
            JsonObject countryJson = convertToJson(c);
            jsonArrayBuilder.add(countryJson);
        }

        JsonArray array = jsonArrayBuilder.build();
        return array.toString();
    }


    @GET
    @Path("/largestpopulations")
    public String getLargestPopulations() {
        WorldService worldServices = ServiceProvider.getWorldService();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Country country : worldServices.get10LargestPopulations()) {
            JsonObject countryJson = convertToJson(country);
            jsonArrayBuilder.add(countryJson);
        }

        JsonArray array = jsonArrayBuilder.build();
        return array.toString();
    }
    @POST
    @RolesAllowed("user")
    @Path("/add")
    public boolean addCountry(@FormParam("country1") String code, @FormParam("country2") String iso3, @FormParam("country3") String nm
            , @FormParam("country4") String cap, @FormParam("country5") String ct, @FormParam("country6") String reg, @FormParam("country7") String sur,
                             @FormParam("country8") String pop, @FormParam("country9") String gov, @FormParam("country10") String lat, @FormParam("country11") String lng) {
        Country c = new Country(code, iso3, nm , cap, ct, reg , Double.parseDouble(sur), Integer.parseInt(pop), gov, Double.parseDouble(lat), Double.parseDouble(lng));
        WorldService worldServices = ServiceProvider.getWorldService();
        return worldServices.save(c);
    }
    @DELETE
    @RolesAllowed("user")
    @Path("/{code}")
    public boolean deleteCountry(@PathParam("code") String code) {
        WorldService worldServices = ServiceProvider.getWorldService();
        Country country = worldServices.getCountryByCode(code);
        return worldServices.delete(country);
    }
    @PUT
    @RolesAllowed("user")
    @Path("/update")
    public boolean updateCountry(@FormParam("country1") String code, @FormParam("country2") String iso3, @FormParam("country3") String nm
            , @FormParam("country4") String cap, @FormParam("country5") String ct, @FormParam("country6") String reg, @FormParam("country7") String sur,
                              @FormParam("country8") String pop, @FormParam("country9") String gov, @FormParam("country10") String lat, @FormParam("country11") String lng) {
        Country c = new Country(code, iso3, nm , cap, ct, reg , Double.parseDouble(sur), Integer.parseInt(pop), gov, Double.parseDouble(lat), Double.parseDouble(lng));
        WorldService worldServices = ServiceProvider.getWorldService();
        System.out.println(c.toString());
        if(worldServices.getCountryByCode(code)==null){
            return false;
        }
        return worldServices.update(c);
    }

}
