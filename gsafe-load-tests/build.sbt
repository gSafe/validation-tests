enablePlugins(GatlingPlugin)

scalaVersion := "2.11.6"
name := "sign-load-tests"
version := "1.0"

libraryDependencies ++= List(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.5" % "test",
  "io.gatling" % "gatling-test-framework" % "2.1.5" % "test",
  "com.typesafe" % "config" % "1.2.1"
)