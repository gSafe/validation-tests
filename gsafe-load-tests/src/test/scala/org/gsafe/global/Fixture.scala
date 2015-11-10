package org.gsafe.global

import com.typesafe.config.ConfigFactory

object Fixture {
  val env = scala.sys.props.get("load.env").getOrElse("integration")
  lazy val config = ConfigFactory.load("application.conf")
  lazy val baseUrl = config.getString(s"$env.baseUrl")
  lazy val rootDir = config.getString(s"$env.rootDir")
  var nbTests : Int = Integer.valueOf(config.getString(s"$env.nbTests"))
  var nbUsers : Int = Integer.valueOf(config.getString(s"$env.nbUsers"))
}
