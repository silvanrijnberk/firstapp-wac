package nl.hu.v1wac.webservices;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.v1wac.model.UserDAO;
import nl.hu.v1wac.model.UserPostgresDaoImpl;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import  javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;


@Path("/authentication")
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/")
    public Response authenticateUser(@FormParam("username") String username, @FormParam("password")String password){
        try {
            UserDAO dao = new UserPostgresDaoImpl();
            String role = dao.getUserRole(username, password);
            if (role == null) {
                throw new IllegalArgumentException("no user found!");
            }
            String token = createToken(username, role);
            String jsonString = Json.createObjectBuilder()
                    .add("JWT", token)
                    .build()
                    .toString();
            return Response.ok(jsonString).build();
        }catch(JwtException | IllegalArgumentException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private String createToken(String username, String role){
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder().setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role",role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
