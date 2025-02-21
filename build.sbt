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
