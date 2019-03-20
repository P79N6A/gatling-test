package audience_insights.test

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import java.io.FileInputStream
import java.util.Properties

class SingleInterestFilterNormalVisitTest extends Simulation {

  val prop = new Properties()
  prop.load(new FileInputStream("src/test/resources/test.properties"))

  val wssid: String = prop.getProperty("wssid");
  val cookie : String = prop.getProperty("cookie");
  val userId: String = "ahuang01@yahoo-inc.com";

  val httpProtocol = http
    .baseUrl("https://api.advertisinginsights.yahoo.com")
    .header("Cookie", cookie)
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")

  val scn = scenario("Single Interest Filter - Normal Visit Scenario")
    .exec(http("get:ai_ur/view/user")
      .get("/aiy-mw/api/ai_ur/view/user?wssid=" + wssid + "&requestUserProvider=bouncer&targetUserProvider=bouncer&provider=bouncer&userId=" + userId + "&region=US"))

    .exec(http("get:/ai/seat-mdms")
      .get("/aiy-mw/api/ai/catalog/mdmIds?wssid=" + wssid + "&provider=bouncer&userId=" + userId))

    .exec(http("get:/ai_ur/regions")
      .get("/aiy-mw/api/ai_ur/regions?wssid=" + wssid + "&provider=bouncer"))


  setUp(scn.inject(rampUsersPerSec(100) to(1000) during(1 minutes), heavisideUsers(1000) during(5 minutes)).protocols(httpProtocol))
}