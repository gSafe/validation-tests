package org.gsafe

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.gsafe.global.Fixture._

class ControlerSimulation extends GsafeSimulation {
  val scn = scenario("Controler").repeat(nbTests) {
    exec(http("controler")
      .get("/coffre/controler?ID_CCFN=coffre_123&ID_UTI=Leon Martin&ID_CONT=documents_perso"))
  }

  setUp(scn.inject(atOnceUsers(nbUsers)).protocols(httpConf))
  
}
