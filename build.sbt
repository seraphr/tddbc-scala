val VERSION = "0.1-SNAPSHOT"

val SCALA_VERSION = "2.10.4"

val ORGANIZATION = "jp.seraphr.tddbc"

val COMMON_DEPENDENCIES = Seq(
    "org.scalatest" %% "scalatest" % "2.1.6" % "test",
    "org.scalacheck" %% "scalacheck" % "1.11.0" % "test"
)

val COMMON_SETTINGS = Seq(
  organization := ORGANIZATION,
  version := VERSION,
  scalaVersion := SCALA_VERSION,
  testOptions in ScctTest += Tests.Argument("-oS", "-u", "target/junit"),
  EclipseKeys.withSource := true,
  EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource,
  libraryDependencies ++= COMMON_DEPENDENCIES
) ++ ScctPlugin.instrumentSettings

// root project
lazy val root = Project(
  id = "tddbc",
  base = file("."),
  settings = Defaults.defaultSettings ++ Seq(name := "tddbc") ++ ScctPlugin.mergeReportSettings
)
