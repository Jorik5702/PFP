package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(
            @DefaultValue ("100") @QueryParam("rounds") int maxRounds
    ) {
        double intermediate = 0.0;
        for(int round = 0; round < maxRounds; round++) {
            intermediate += Math.sqrt(round);
        }
        return "{" +
                " \"Foo\":\""+Double.toHexString(intermediate)+"\""+
                "}";
    }
}