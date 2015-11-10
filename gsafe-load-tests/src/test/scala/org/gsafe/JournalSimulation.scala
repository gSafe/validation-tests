package org.gsafe

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.gsafe.global.Fixture._

class JournalSimulation extends GsafeSimulation {
  val scn = scenario("Lire journal").repeat(nbTests) {
    exec(http("journal_idu")
      .get("/coffre/controler?ID_CCFN=coffre_123&ID_UTI=Leon Martin&IDU=123&ID_CONT=documents_perso"))
  }.repeat(nbTests) {
    exec(http("journal_plage_on")
      .get("/coffre/controler?ID_CCFN=coffre_123&ID_UTI=Leon Martin&ID_ON_UTI=120-124&ID_CONT=documents_perso"))
  }.repeat(nbTests) {
    exec(http("journal_plage_date")
      .get("/coffre/controler?ID_CCFN=coffre_123&ID_UTI=Leon Martin&DateEtHeure=15/01/2014:00:00-31/01/2014:23:59&ID_CONT=documents_perso"))
  }

  setUp(scn.inject(atOnceUsers(nbUsers)).protocols(httpConf))
}
