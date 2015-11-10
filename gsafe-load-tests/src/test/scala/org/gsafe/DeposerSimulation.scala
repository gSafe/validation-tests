package org.gsafe

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.gsafe.global.Fixture._

class DeposerSimulation extends GsafeSimulation {
  val scn = scenario("Deposer").repeat(nbTests) {
    exec(http("deposer")
      .post("/coffre/deposer")
      .bodyPart(RawFileBodyPart("file", rootDir ++ "test.pdf"))
      .bodyPart(StringBodyPart("ID_CCFN", "coffre_123"))
      .bodyPart(StringBodyPart("ID_UTI", "Leon Martin"))
      .bodyPart(StringBodyPart("ID_CONT", "documents_perso"))
    )
  }.repeat(nbTests) {
    exec(http("deposer_mode_controlé")
      .post("/coffre/deposer")
      .bodyPart(RawFileBodyPart("file", rootDir ++ "test.pdf"))
      .bodyPart(StringBodyPart("ID_CCFN", "coffre_123"))
      .bodyPart(StringBodyPart("ID_UTI", "Leon Martin"))
      .bodyPart(StringBodyPart("ID_CONT", "documents_perso"))
      .bodyPart(StringBodyPart("Algorithme", "SHA1"))
      .bodyPart(StringBodyPart("Empreinte", "da39a3ee5e6b4bd3255bfef95601890afd879"))
    )
  }

  setUp(scn.inject(atOnceUsers(nbUsers)).protocols(httpConf))
  
}
