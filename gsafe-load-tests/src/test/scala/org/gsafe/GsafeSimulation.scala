package org.gsafe

import org.gsafe.global.Fixture
import Fixture._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

abstract class GsafeSimulation extends Simulation {

  val httpConf = http
    .baseURL(baseUrl)

}
