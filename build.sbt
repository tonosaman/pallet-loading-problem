/** Project */
name := "plp"

organization := "org.scala"

version := "0.1"

scalaVersion := "2.9.2"

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases")

libraryDependencies ++= Seq("com.typesafe.akka" % "akka-actor" % "2.0.3")

scalacOptions ++= Seq("-unchecked", "-deprecation")

// define the statements initially evaluated when entering 'console', 'console-quick', or 'console-project'
initialCommands := """
  import System.{currentTimeMillis => now}
  def time[T](f: => T): T = {
    val start = now
    try { f } finally { println("Elapsed: " + (now - start)/1000.0 + " s") }
  }
"""
