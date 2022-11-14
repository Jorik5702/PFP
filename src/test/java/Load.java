import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import java.time.Duration;

public class Load extends Simulation {
    public static void main(String[] args) {
        System.out.println("Load test");
        GatlingPropertiesBuilder props = new GatlingPropertiesBuilder();
        props.simulationClass(Load.class.getName());
        Gatling.fromMap(props.build());

    }
    HttpProtocolBuilder httpProtocol =
            http
                    .baseUrl("https://pfp-nivswhaqta-oa.a.run.app")
                    .acceptHeader("text/html,application/json;q=0.9,*/*;q=0.8")
                    .doNotTrackHeader("1")
                    .acceptEncodingHeader("gzip, deflate");

    ScenarioBuilder scn =
            scenario("Scenario Name")
                    .exec(http("request_1")
                            .get("/hello")
                    );

    {
        setUp(
                scn.injectOpen(
                        atOnceUsers(1),
                        nothingFor(2),
                        atOnceUsers(2)
                        )
                        .protocols(httpProtocol)
        );
    }
}
