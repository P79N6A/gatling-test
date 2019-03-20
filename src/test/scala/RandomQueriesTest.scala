package audience_insights.test

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import java.util.Properties
import java.io.FileInputStream

class RandomQueriesTest extends Simulation {
  val prop = new Properties()
  prop.load(new FileInputStream("src/test/resources/test.properties"))

  val wssid: String = prop.getProperty("wssid");
  val cookie: String = prop.getProperty("cookie");
  val userId: String = "ahuang01@yahoo-inc.com";

  val queryPayloads = tsv("src/test/resources/queryPayload.tsv").random

  val httpProtocol = http
    .header("Cookie", cookie)
//    .baseUrl("https://api.advertisinginsights.yahoo.com")
    .baseUrl("https://mw19.diy.bf1.yahoo.com")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
  var count = 0;
  val scn = scenario("Random Queries Scenario")
    .feed(queryPayloads)
    .exec(http("post:/ai/insights:${moduleId}:${FilterType}")
    .post("/aiy-mw/api/ai/insights?wssid=" + wssid)
    .body(StringBody("""${payload}""")).asJson)


  setUp(scn.inject(
    rampConcurrentUsers(1) to (100) during(10 seconds),
    constantConcurrentUsers(100) during(1 minutes),
    rampConcurrentUsers(100) to (1) during(10 seconds)
  ).protocols(httpProtocol))
}