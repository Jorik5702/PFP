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
                            .get("/process")
                            .queryParam("rounds", "10")
                    );

    {
        setUp(
                scn.injectOpen(
                                nothingFor(1),
                                constantUsersPerSec(1).during(Duration.ofSeconds(20)),
                                constantUsersPerSec(4).during(Duration.ofSeconds(20)),
                                constantUsersPerSec(8).during(Duration.ofSeconds(20)),
                                constantUsersPerSec(12).during(Duration.ofSeconds(20)),
                                constantUsersPerSec(16).during(Duration.ofSeconds(20)),
                                constantUsersPerSec(20).during(Duration.ofSeconds(20)),
                                nothingFor(10)
                        )
                        .protocols(httpProtocol)
        );
    }
}
