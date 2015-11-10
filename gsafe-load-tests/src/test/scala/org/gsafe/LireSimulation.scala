package org.gsafe

import org.gsafe.global.Fixture._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class LireSimulation extends GsafeSimulation {
  val scn = scenario("Lire").repeat(nbTests) {
    exec(http("lire")
      .get("/coffre/lire?ID_CCFN=coffre_123&ID_UTI=Leon Martin&IDU=10"))
  }

  setUp(scn.inject(atOnceUsers(nbUsers)).protocols(httpConf))
}
