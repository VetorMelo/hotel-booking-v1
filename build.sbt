name := "hotel-booking"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.2.20",
  "com.typesafe.akka" %% "akka-http" % "10.2.4",
  "com.typesafe.akka" %% "akka-stream" % "2.6.14",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0"

)

libraryDependencySchemes += "org.scala-lang.modules" %% "scala-parser-combinators" % "early-semver"
dependencyOverrides += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

