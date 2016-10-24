
lazy val commonSettings = Seq(
  organization := "de.htwg",
  version := "0.1",
  scalaVersion := "2.11.8",

  libraryDependencies ++= Seq(
  //  "org.scalactic" %% "scalactic" % "3.0.0",
    //  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
    //  "org.scala-lang.modules" %% "scala-swing" % "1.0.2",
    //  "org.json4s" %% "json4s-jackson" % "3.4.2",
    //  "org.mongodb" % "mongo-java-driver" % "3.3.0",
    //  "org.mongodb" % "bson" % "3.3.0",
    // "ch.qos.logback" % "logback-classic" % "1.1.7",
    // "org.clapper" %% "grizzled-slf4j" % "1.2.0",
    // "org.webjars" %% "webjars-play" % "2.5.0-3",
     "com.typesafe.akka" %% "akka-cluster-tools" % "2.4.11"
  )
)

lazy val TicTacToe = (project in file(".")).
  settings(commonSettings: _*).settings(name:="TicTacToe")
  enablePlugins(PlayScala)

lazy val web = (project in file("web")).
  settings(commonSettings: _*).
  settings(
    name := "web"
  ).  enablePlugins(PlayScala)
