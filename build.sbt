name := """StoreNRent"""

version := "1.0-SNAPSHOT"

autoScalaLibrary := false

scalaVersion := "2.10.3"



libraryDependencies ++= Seq(
  javaJdbc,
  cache
)

resolvers += Resolver.sonatypeRepo("releases")

play.Project.playJavaSettings