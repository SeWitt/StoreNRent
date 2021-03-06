resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.3")

// web plugins

addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0-RC3")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.0-RC2")

addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.0-RC2")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.0-RC3")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.0.0-RC2")

addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.0.0-RC2")

libraryDependencies += "org.javassist" % "javassist" % "3.18.2-GA"
