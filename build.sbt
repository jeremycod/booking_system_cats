

name := "bookingSystemCats"
 
version := "1.0" 
      
lazy val `bookingsystemcats` = (project in file(".")).enablePlugins(PlayScala)

      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.6"
libraryDependencies ++= Settings.Dependencies
libraryDependencies ++= Seq( evolutions, ehcache , ws , specs2 % Test , guice,
)
      