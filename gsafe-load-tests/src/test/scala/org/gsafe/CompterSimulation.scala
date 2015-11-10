package org.gsafe

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.gsafe.global.Fixture._

class CompterSimulation extends GsafeSimulation {
  val scn = scenario("Compter").repeat(nbTests) {
    exec(http("compter_plage_on")
      .get("/coffre/compter?ID_CCFN=coffre_123&ID_UTI=Leon Martin&ID_ON_UTI=120-124&ID_CONT=documents_perso"))
  }.repeat(nbTests) {
    exec(http("compter_plage_date")
      .get("/coffre/lister?ID_CCFN=coffre_123&ID_UTI=Leon Martin&DateEtHeure=15/01/2014:00:00-31/01/2014:23:59&ID_CONT=documents_perso"))
  }

  setUp(scn.inject(atOnceUsers(nbUsers)).protocols(httpConf))
  
}
