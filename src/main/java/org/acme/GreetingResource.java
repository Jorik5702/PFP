package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class.getName());

    static {
        LOG.info("Start PFP demo service");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(
            @DefaultValue ("100") @QueryParam("rounds") int maxRounds
    ) {
        LOG.info("processin request with rounds="+maxRounds);

        double intermediate = 0.0;
        for(int round = 0; round < maxRounds; round++) {
            for(int inner = 0; inner < 1000000; inner++) {
                intermediate += Math.sqrt(round+inner);
            }
            intermediate += Math.sqrt(round);
        }
        return "{" +
                " \"Foo\":\""+Double.toHexString(intermediate)+"\""+
                "}";
    }
}