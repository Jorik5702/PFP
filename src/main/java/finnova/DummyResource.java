package finnova;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/process")
public class DummyResource {

    private static final Logger LOG = Logger.getLogger(DummyResource.class.getName());

    static {
        LOG.info("Start PFP demo service");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String process(
            @DefaultValue ("10") @QueryParam("rounds") int maxRounds
    ) {
        LOG.info("processing request with rounds="+maxRounds);

        double intermediate = 0.0;
        for(int round = 0; round < maxRounds; round++) {
            for(int inner = 0; inner < 1000000; inner++) {
                intermediate += Math.sqrt(round+inner);
            }
        }
        return "{" +
                " \"result\":\""+Double.toHexString(intermediate)+"\""+
                "}";
    }
}