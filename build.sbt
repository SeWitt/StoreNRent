name := """StoreNRent"""

version := "1.0-SNAPSHOT"

autoScalaLibrary := false

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  javaCore,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.5.Final",
  "dom4j" % "dom4j" % "1.6.1",
  "org.hibernate.common" % "hibernate-commons-annotations" % "4.0.4.Final",
  "org.hibernate.javax.persistence" % "hibernate-jpa-2.0-api" % "1.0.1.Final",
  "org.hibernate" % "hibernate-core" % "4.3.5.Final",
  "org.javassist" % "javassist" % "3.18.1-GA",
  "org.jboss.logging" % "jboss-logging-processor" % "1.2.0.Final",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.seleniumhq.selenium" % "selenium-java" % "2.5.0" % "test",
  "com.h2database" % "h2" % "1.4.178" % "test",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

resolvers += Resolver.sonatypeRepo("releases")

play.Project.playJavaSettings

javaOptions ++= Seq(
	"-Xms1024M", 
	"-Xmx2048M", 
	"-XX:MaxPermSize=2048M"
)