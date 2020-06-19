package nl.hu.v1wac.webservices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.StringReader;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        boolean isSecure = requestContext.getSecurityContext().isSecure();
        MySecurityContext msc = new MySecurityContext("Unkown","guest", isSecure);

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authHeader != null && authHeader.startsWith("Bearer")){
            String token = authHeader.substring("Bearer".length()).trim();
            JsonObject body = Json.createReader(new StringReader(token)).readObject();

            try {
                JwtParser parser = Jwts.parser().setSigningKey(AuthenticationResource.key);
                Claims claims = parser.parseClaimsJws(body.getString("JWT")).getBody();
                String user = claims.getSubject();
                String role = claims.get("role").toString();
                msc = new MySecurityContext(user, role, isSecure);
            }catch (JwtException | IllegalArgumentException e){
                System.out.println("Invalid JWT, processing as guest"+"\n error: "+e);
            }
        }
        requestContext.setSecurityContext(msc);
    }
}
