name := """tmsp"""
organization := "com.teste"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "6.0.0-RC2" % Test
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.24" // Versão mais recente
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.3" // Versão mais recente
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.32" // Versão mais recente
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3" // Versão mais recente
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4" // Versão mais recente
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.3" // Versão mais recente


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.teste.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.teste.binders._"
