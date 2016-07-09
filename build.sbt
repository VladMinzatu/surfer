name := "surfer"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.1"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "1.6.1"
libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5")

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
    