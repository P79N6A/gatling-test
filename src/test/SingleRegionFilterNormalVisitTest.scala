package audience_insights.test

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import java.io.FileInputStream
import java.util.Properties

class SingleRegionFilterNormalVisitTest extends Simulation {
  val prop = new Properties()
  prop.load(new FileInputStream("src/test/resources/test.properties"))

  val wssid: String = prop.getProperty("wssid");
  val cookie : String = prop.getProperty("cookie");
  val userId: String = "ahuang01@yahoo-inc.com";

  val httpProtocol = http
    .baseUrl("https://api.advertisinginsights.yahoo.com")
    .header("Cookie", cookie)
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")

  val scn = scenario("Single Location Filter - Normal Visit Scenario")
    .exec(http("get:ai_ur/view/user")
      .get("/aiy-mw/api/ai_ur/view/user?wssid=" + wssid + "&requestUserProvider=bouncer&targetUserProvider=bouncer&provider=bouncer&userId=" + userId + "&region=US"))

    .exec(http("get:/ai/seat-mdms")
      .get("/aiy-mw/api/ai/catalog/mdmIds?wssid=" + wssid + "&provider=bouncer&userId=" + userId))

    .exec(http("get:/ai_ur/regions")
      .get("/aiy-mw/api/ai_ur/regions?wssid=" + wssid + "&provider=bouncer"))

    .exec(http("post:/ai/insights:US_AGE_GENDER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"AGE_GENDER","moduleId":"US_AGE_GENDER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_GENDER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"GENDER","moduleId":"US_GENDER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_STATE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"STATE","moduleId":"US_STATE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_AGE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"AGE","moduleId":"US_AGE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_CITY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"CITY","moduleId":"US_CITY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_DMA")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"DMA","moduleId":"US_DMA","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_RESIDENCE_LENGTH")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"RESIDENCE_LENGTH","moduleId":"US_RESIDENCE_LENGTH","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_RESIDENCE_OWNERSHIP")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"RESIDENCE_OWNERSHIP","moduleId":"US_RESIDENCE_OWNERSHIP","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_AUTO_OWNER_STYLE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"AUTO_OWNER_STYLE","moduleId":"US_AUTO_OWNER_STYLE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_AUTO_OWNER_MAKE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"AUTO_OWNER_MAKE","moduleId":"US_AUTO_OWNER_MAKE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_AUTOMOTIVE_MAKE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"AUTOMOTIVE_MAKE","moduleId":"US_AUTOMOTIVE_MAKE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_AUTOMOTIVE_MOTORCYCLE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"AUTOMOTIVE_MOTORCYCLE","moduleId":"US_AUTOMOTIVE_MOTORCYCLE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_FINANCE_BANKING")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"FINANCE_BANKING","moduleId":"US_FINANCE_BANKING","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_FINANCE_SPENDING")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"FINANCE_SPENDING","moduleId":"US_FINANCE_SPENDING","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_FINANCE_STOCK_TICKER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"FINANCE_STOCK_TICKER","moduleId":"US_FINANCE_STOCK_TICKER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_HOUSE_HOLD_INCOME_LEVEL2")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"HOUSE_HOLD_INCOME_LEVEL2","moduleId":"US_HOUSE_HOLD_INCOME_LEVEL2","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_LIFE_STAGES_LEVEL2")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"LIFE_STAGES_LEVEL2","moduleId":"US_LIFE_STAGES_LEVEL2","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_LIFE_STAGES_PARENTS")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"LIFE_STAGES_PARENTS","moduleId":"US_LIFE_STAGES_PARENTS","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PURCHASE_LEVEL1")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PURCHASE_LEVEL1","moduleId":"US_PURCHASE_LEVEL1","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PURCHASE_LEVEL2")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PURCHASE_LEVEL2","moduleId":"US_PURCHASE_LEVEL2","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PURCHASE_AGE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PURCHASE_AGE","moduleId":"US_PURCHASE_AGE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PURCHASE_AGE_GENDER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PURCHASE_AGE_GENDER","moduleId":"US_PURCHASE_AGE_GENDER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PURCHASE_GENDER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PURCHASE_GENDER","moduleId":"US_PURCHASE_GENDER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_ITEM_NAME")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"ITEM_NAME","moduleId":"US_ITEM_NAME","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SIGNIFICANT_ITEM_NAME")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SIGNIFICANT_ITEM_NAME","moduleId":"US_SIGNIFICANT_ITEM_NAME","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MATCHING_ITEM_NAME")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MATCHING_ITEM_NAME","moduleId":"US_MATCHING_ITEM_NAME","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PURCHASE_DOMAIN")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PURCHASE_DOMAIN","moduleId":"US_PURCHASE_DOMAIN","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PAYMENT_METHOD")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PAYMENT_METHOD","moduleId":"US_PAYMENT_METHOD","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_ENTERTAINMENT_EVENT_TYPE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"ENTERTAINMENT_EVENT_TYPE","moduleId":"US_ENTERTAINMENT_EVENT_TYPE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_ENTERTAINMENT_LIVE_EVENT")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"ENTERTAINMENT_LIVE_EVENT","moduleId":"US_ENTERTAINMENT_LIVE_EVENT","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_ENTERTAINMENT_MOVIE_EVENT")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"ENTERTAINMENT_MOVIE_EVENT","moduleId":"US_ENTERTAINMENT_MOVIE_EVENT","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_EXTERNAL_DOMAIN")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"EXTERNAL_DOMAIN","moduleId":"US_EXTERNAL_DOMAIN","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_APP_INSTALL_CATEGORY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"APP_INSTALL_CATEGORY","moduleId":"US_APP_INSTALL_CATEGORY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_APP_ACTIVITY_CATEGORY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"APP_ACTIVITY_CATEGORY","moduleId":"US_APP_ACTIVITY_CATEGORY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_APP_ACTIVITY_TREND_DAILY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"APP_ACTIVITY_TREND_DAILY","moduleId":"US_APP_ACTIVITY_TREND_DAILY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_APP_ACTIVITY_INTRA_DAY_TREND")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"APP_ACTIVITY_INTRA_DAY_TREND","moduleId":"US_APP_ACTIVITY_INTRA_DAY_TREND","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_DEVICE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"DEVICE","moduleId":"US_DEVICE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_CLICKED_USER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"CLICKED_USER","moduleId":"US_CLICKED_USER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_CONVERTED_USER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"CONVERTED_USER","moduleId":"US_CONVERTED_USER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PREMIUM_SEGMENT_LEVEL1")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PREMIUM_SEGMENT_LEVEL1","moduleId":"US_PREMIUM_SEGMENT_LEVEL1","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PREMIUM_SEGMENT_LEVEL2")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PREMIUM_SEGMENT_LEVEL2","moduleId":"US_PREMIUM_SEGMENT_LEVEL2","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_IN_MARKET_AUTO_STYLE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"IN_MARKET_AUTO_STYLE","moduleId":"US_IN_MARKET_AUTO_STYLE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_IN_MARKET_AUTO_MAKE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"IN_MARKET_AUTO_MAKE","moduleId":"US_IN_MARKET_AUTO_MAKE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_STORE_CATEGORY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"STORE_CATEGORY","moduleId":"US_STORE_CATEGORY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_STORE_NAME")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"STORE_NAME","moduleId":"US_STORE_NAME","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_IN_MARKET_HOME_LEVEL3")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"IN_MARKET_HOME_LEVEL3","moduleId":"US_IN_MARKET_HOME_LEVEL3","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_HOTEL_BRAND")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"HOTEL_BRAND","moduleId":"US_HOTEL_BRAND","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_AIRLINE_NAME")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"AIRLINE_NAME","moduleId":"US_AIRLINE_NAME","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_TRAVEL_DOMAIN")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"TRAVEL_DOMAIN","moduleId":"US_TRAVEL_DOMAIN","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_ARRIVAL_AIRPORT")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"ARRIVAL_AIRPORT","moduleId":"US_ARRIVAL_AIRPORT","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_TRAVEL_TYPE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"TRAVEL_TYPE","moduleId":"US_TRAVEL_TYPE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_TRAVELLER_TYPE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"TRAVELLER_TYPE","moduleId":"US_TRAVELLER_TYPE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_DEPARTURE_AIRPORT")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"DEPARTURE_AIRPORT","moduleId":"US_DEPARTURE_AIRPORT","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SEARCH_CATEGORY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SEARCH_CATEGORY","moduleId":"US_SEARCH_CATEGORY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SEARCH_TERM")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SEARCH_TERM","moduleId":"US_SEARCH_TERM","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SIGNIFICANT_SEARCH_TERM")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SIGNIFICANT_SEARCH_TERM","moduleId":"US_SIGNIFICANT_SEARCH_TERM","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MATCHING_SEARCH_TERM")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MATCHING_SEARCH_TERM","moduleId":"US_MATCHING_SEARCH_TERM","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SEARCH_AGE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SEARCH_AGE","moduleId":"US_SEARCH_AGE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SEARCH_AGE_GENDER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SEARCH_AGE_GENDER","moduleId":"US_SEARCH_AGE_GENDER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SEARCH_GENDER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SEARCH_GENDER","moduleId":"US_SEARCH_GENDER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SEARCH_WIKI")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SEARCH_WIKI","moduleId":"US_SEARCH_WIKI","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_SEARCH_TAXO")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"SEARCH_TAXO","moduleId":"US_SEARCH_TAXO","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_OO_APP_NAME_TREND_DAILY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"OO_APP_NAME_TREND_DAILY","moduleId":"US_OO_APP_NAME_TREND_DAILY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_OO_APP_NAME_INTRA_DAY_TREND")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"OO_APP_NAME_INTRA_DAY_TREND","moduleId":"US_OO_APP_NAME_INTRA_DAY_TREND","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MOBILE_APP_INSTALL_CATEGORY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MOBILE_APP_INSTALL_CATEGORY","moduleId":"US_MOBILE_APP_INSTALL_CATEGORY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MOBILE_APP_ACTIVITY_CATEGORY")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MOBILE_APP_ACTIVITY_CATEGORY","moduleId":"US_MOBILE_APP_ACTIVITY_CATEGORY","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MOBILE_DEVICE_CARRIER")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MOBILE_DEVICE_CARRIER","moduleId":"US_MOBILE_DEVICE_CARRIER","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MOBILE_DEVICE_MAKE")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MOBILE_DEVICE_MAKE","moduleId":"US_MOBILE_DEVICE_MAKE","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MOBILE_DEVICE_MODEL")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MOBILE_DEVICE_MODEL","moduleId":"US_MOBILE_DEVICE_MODEL","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MOBILE_OS_NAME")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MOBILE_OS_NAME","moduleId":"US_MOBILE_OS_NAME","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_MOBILE_OS_VERSION")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"MOBILE_OS_VERSION","moduleId":"US_MOBILE_OS_VERSION","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_OO_APP_NAME")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"OO_APP_NAME","moduleId":"US_OO_APP_NAME","userPreference":{"country":"US"},"esTier":1,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_YAHOO_SEGMENT_ID")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"YAHOO_SEGMENT_ID","moduleId":"US_YAHOO_SEGMENT_ID","userPreference":{"country":"US"},"esTier":2,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_TP_SEGMENT_ID")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"TP_SEGMENT_ID","moduleId":"US_TP_SEGMENT_ID","userPreference":{"country":"US"},"esTier":2,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_PREMIUM_SEGMENT_LEVEL3")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"PREMIUM_SEGMENT_LEVEL3","moduleId":"US_PREMIUM_SEGMENT_LEVEL3","userPreference":{"country":"US"},"esTier":2,"userId":"ahuang01@yahoo-inc.com"}""")))

    .exec(http("post:/ai/insights:US_FP_SEGMENT_ID")
      .post("/aiy-mw/api/ai/insights?wssid="+wssid)
      .body(StringBody("""{"target":[{"dim":"STATE","values":["2347591"]}],"base":[{"dim":"STATE","values":["2347563"]}],"dim":"FP_SEGMENT_ID","moduleId":"US_FP_SEGMENT_ID","userPreference":{"country":"US"},"esTier":2,"userId":"ahuang01@yahoo-inc.com","include":{"mdmIds":["21774"]}}""")))

  setUp(scn.inject(rampUsersPerSec(100) to(1000) during(1 minutes), heavisideUsers(1000) during(5 minutes)).protocols(httpProtocol))
}