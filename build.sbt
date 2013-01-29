import com.typesafe.sbt.SbtStartScript

organization := "com.micronautics"

name := "awss3"

crossPaths := false

version := "0.1.1-SNAPSHOT"

scalaVersion := "2.10.0"

scalacOptions ++= Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-target:jvm-1.7", "-unchecked",
    "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint")

javacOptions ++= Seq("-Xlint:deprecation", "-Xlint:unchecked", "-source", "1.7", "-target", "1.7", "-g:vars")

scalacOptions in (Compile, doc) <++= baseDirectory.map {
  (bd: File) => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", "https://github.com/mslinn/AwsS3/tree/master€{FILE_PATH}.scala"
  )
}

libraryDependencies ++= Seq(
  "com.amazonaws"                 %  "aws-java-sdk"        % "1.3.30"  withSources(),
  "com.codahale"                  %  "jerkson_2.9.1"       % "0.5.0",
  "commons-io"                    %  "commons-io"          % "2.4"     withSources(),
  "commons-lang"                  %  "commons-lang"        % "2.6"     withSources(),
  "junit"                         %  "junit"               % "4.10"    % "test" withSources(),
  "org.clapper"                   %% "grizzled-scala"      % "1.1.2"   withSources(),
  "org.scalatest"                 %% "scalatest"           % "2.0.M5b" % "test" withSources(),
  "org.scala-tools"               % "time"                 % "2.7.4-0.1"
)

publishTo <<= (version) { version: String =>
   val scalasbt = "http://scalasbt.artifactoryonline.com/scalasbt/"
   val (name, url) = if (version.contains("-SNAPSHOT"))
                       ("sbt-plugin-snapshots", scalasbt+"sbt-plugin-snapshots")
                     else
                       ("sbt-plugin-releases", scalasbt+"sbt-plugin-releases")
   Some(Resolver.url(name, new URL(url))(Resolver.ivyStylePatterns))
}

publishMavenStyle := false

seq(SbtStartScript.startScriptForClassesSettings: _*)

//logLevel := Level.Error

// define the statements initially evaluated when entering 'console', 'console-quick', or 'console-project'
initialCommands := """
  """

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
//logLevel in compile := Level.Warn
