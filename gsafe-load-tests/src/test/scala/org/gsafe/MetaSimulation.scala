package org.gsafe

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.gsafe.global.Fixture._

class MetaSimulation extends GsafeSimulation {
  val scn = scenario("Metadata").repeat(nbTests) {
    exec(http("metadata")
      .get("/coffre/meta?ID_CCFN=coffre_123&ID_UTI=Leon Martin&IDU=10&ID_CONT=documents_perso"))
  }

  setUp(scn.inject(atOnceUsers(nbUsers)).protocols(httpConf))
  
}
