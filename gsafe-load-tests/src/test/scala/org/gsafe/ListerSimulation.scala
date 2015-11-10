package org.gsafe

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.gsafe.global.Fixture._

class ListerSimulation extends GsafeSimulation {
  val scn = scenario("Lister").repeat(nbTests) {
    exec(http("liste_complète")
      .get("/coffre/lister?ID_CCFN=coffre_123&ID_UTI=Leon Martin&ID_CONT=documents_perso"))
  }.repeat(nbTests) {
    exec(http("lister_idu")
      .get("/coffre/lister?ID_CCFN=coffre_123&ID_UTI=Leon Martin&IDU=123&ID_CONT=documents_perso"))
  }.repeat(nbTests) {
    exec(http("lister_plage_on")
      .get("/coffre/lister?ID_CCFN=coffre_123&ID_UTI=Leon Martin&ID_ON_UTI=120-124&ID_CONT=documents_perso"))
  }.repeat(nbTests) {
    exec(http("lister_plage_date")
      .get("/coffre/lister?ID_CCFN=coffre_123&ID_UTI=Leon Martin&DateEtHeure=15/01/2014:00:00-31/01/2014:23:59&ID_CONT=documents_perso"))
  }

  setUp(scn.inject(atOnceUsers(nbUsers)).protocols(httpConf))
  
}
