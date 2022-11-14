package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        double intermediate = 0.0;
        for(int round = 0; round < 100; round++) {
            intermediate += Math.sqrt(round);
        }
        return "{" +
                " \"Foo\":\""+Double.toHexString(intermediate)+"\""+
                "}";
    }
}