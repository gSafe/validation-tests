package org.gsafe

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.gsafe.global.Fixture._

class DetruireSimulation extends GsafeSimulation {
  val scn = scenario("Detruire").repeat(nbTests) {
    exec(http("detruire")
      .post("/coffre/detruire")
      .queryParam("ID_CCFN", "coffre_123")
      .queryParam("ID_UTI", "Leon Martin")
      .queryParam("IDU", "10")
    )
  }

  setUp(scn.inject(atOnceUsers(nbUsers)).protocols(httpConf))
  
}
