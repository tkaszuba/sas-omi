scalaVersion := "2.12.2"

organization := "com.sas"

name := "sas.oma.omi"

version := "1.0.0"

packageBin in Compile := file(s"${name.value}.jar")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-optimise"
)

javaOptions += "-Xmx3G"

libraryDependencies ++= Seq(
)

/**
 * Force sbt to use scala 2.11.5,
 * otherwise, some dependence will upgrade scala version to 2.11.7
 * in which `sort1` does not exist
 */
