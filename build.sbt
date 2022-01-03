import Dependencies._
import org.scalajs.jsdependencies.sbtplugin.JSDependenciesPlugin.autoImport._

ThisBuild / scalaVersion     := "2.12.6"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, JSDependenciesPlugin)
  .settings(
    name := "scala_test",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0",
    // npmDependencies in Compile += "framework7" -> "6.3.12",
    scalaJSUseMainModuleInitializer := true,
    // scalaJSModuleKind := ModuleKind.CommonJSModule,
    libraryDependencies += "org.querki" %%% "jquery-facade" % "2.0",
    libraryDependencies += "org.querki" %%% "querki-jsext" % "0.10",
    jsDependencies += "org.webjars" % "jquery" % "2.2.1" / "jquery.js" minified "jquery.min.js",
    scalaJSLinkerConfig := { scalaJSLinkerConfig.value.withModuleKind(ModuleKind.CommonJSModule) },
    version in webpack := "4.28.4",
    // webpackBundlingMode := BundlingMode.Application()
  )





// Uncomment the following for publishing to Sonatype.
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for more detail.

// ThisBuild / description := "Some descripiton about your project."
// ThisBuild / licenses    := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
// ThisBuild / homepage    := Some(url("https://github.com/example/project"))
// ThisBuild / scmInfo := Some(
//   ScmInfo(
//     url("https://github.com/your-account/your-project"),
//     "scm:git@github.com:your-account/your-project.git"
//   )
// )
// ThisBuild / developers := List(
//   Developer(
//     id    = "Your identifier",
//     name  = "Your Name",
//     email = "your@email",
//     url   = url("http://your.url")
//   )
// )
// ThisBuild / pomIncludeRepository := { _ => false }
// ThisBuild / publishTo := {
//   val nexus = "https://oss.sonatype.org/"
//   if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
//   else Some("releases" at nexus + "service/local/staging/deploy/maven2")
// }
// ThisBuild / publishMavenStyle := true
