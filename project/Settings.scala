import sbt.Keys.libraryDependencies
import sbt._
object Settings {
object V {
  val playSlickVersion = "5.0.0"
  val mysqlVersion = "8.0.15"
  val alpakkaCsvVersion = "2.0.2"
  val alpakkaSlickVersion = "3.0.2"
  val akkaVersion = "2.6.14"
  val enumeratumVersion = "1.7.0"
  val slickVersion = "3.3.3"
  val slickPg = "0.19.4"
  val catsVersion = "2.2.0"

}

val Dependencies: Seq[sbt.librarymanagement.ModuleID] = Seq(
  "com.typesafe.slick" %% "slick" % V.slickVersion,
  "com.typesafe.slick" %% "slick" % V.slickVersion % Test,
  "com.typesafe.slick" %% "slick-codegen" % V.slickVersion,
  "com.typesafe.play" %% "play-slick" % V.playSlickVersion,
  "com.typesafe.play" %% "play-slick-evolutions" % V.playSlickVersion,
  "com.github.tminglei" %% "slick-pg" % V.slickPg,
  "com.github.tminglei" %% "slick-pg_play-json" % V.slickPg,
  "org.typelevel" %% "cats-core" % V.catsVersion,
  "org.typelevel" %% "cats-effect" % "3.3.12",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  // "mysql" % "mysql-connector-java" % V.mysqlVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
  "org.postgresql" % "postgresql" % "42.2.18"
  )

}