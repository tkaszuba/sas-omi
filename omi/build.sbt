scalaVersion := "2.12.2"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

fork := true

javaOptions += "-Xmx3G"

// retrieveManaged := true

cancelable in Global := true

parallelExecution in Test := false

libraryDependencies ++= Seq(
  "log4j" % "log4j" % "1.2.17",
  "com.sas" %% "sas-core" % "1.0.0",
  "com.sas" %% "sas-entities" % "1.0.0",
  "com.sas" %% "sas-oma-joma" % "1.0.0",
  "com.sas" %% "sas-oma-joma-rmt" % "1.0.0",
  "com.sas" %% "sas-oma-omi" % "1.0.0",
  "com.sas" %% "sas-security-sspi" % "1.0.0",
  "com.sas" %% "sas-svc-connection" % "1.0.0"
)

/**
 * Force sbt to use scala 2.11.5,
 * otherwise, some dependence will upgrade scala version to 2.11.7
 * in which `sort1` does not exist
 */
