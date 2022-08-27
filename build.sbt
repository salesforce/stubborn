val scala212Version = "2.12.16"
val scala213Version = "2.13.8"

val slf4jVersion = "1.7.+"

val scalaTestArtifact      = "org.scalatest"    %% "scalatest"    % "3.2.+"       % Test
val slf4jApiArtifact       = "org.slf4j"        %  "slf4j-api"    % slf4jVersion
val slf4jSimpleArtifact    = "org.slf4j"        %  "slf4j-simple" % slf4jVersion

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  pomIncludeRepository := { _ => false },
  publishTo := sonatypePublishToBundle.value,
  licenses := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0")),
  homepage := Some(url("https://github.com/salesforce/stubborn")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/salesforce/stubborn"),
      "scm:git:git@github.com:salesforce/stubborn.git"
    )
  ),
  credentials += Credentials(
    "Sonatype Nexus Repository Manager",
    "oss.sonatype.org",
    sys.env.getOrElse("SONATYPE_USERNAME",""),
    sys.env.getOrElse("SONATYPE_PASSWORD","")
  ),
  developers := List(
    Developer(
      id = "realstraw",
      name = "Kexin Xie",
      email = "kexin.xie@salesforce.com",
      url = url("http://github.com/realstraw")
    )
  ),
  useGpgPinentry := true
)

lazy val noPublishSettings = Seq(
  publishArtifact := false,
  publish := {},
  publishLocal := {},
  publishTo := None
)

lazy val commonSettings = Seq(
  scalacOptions ++= Seq("-deprecation", "-feature", "-Xlint", "-Xfatal-warnings"),
  scalaVersion := scala212Version,
  crossScalaVersions := Seq(scala212Version, scala213Version),
  libraryDependencies += scalaTestArtifact,
  organization := "com.krux",
  headerLicense := Some(HeaderLicense.Custom(
    """|Copyright (c) 2018, salesforce.com, inc.
       |All rights reserved.
       |SPDX-License-Identifier: BSD-3-Clause
       |For full license text, see the LICENSE file in the repo root or https://opensource.org/licenses/BSD-3-Clause
       |""".stripMargin
  ))
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(publishSettings: _*).
  settings(
    name := "stubborn",
    libraryDependencies += slf4jApiArtifact
  )

lazy val examples = (project in file("examples")).
  settings(commonSettings: _*).
  settings(noPublishSettings: _*).
  settings(
    name := "stubborn-examples",
    libraryDependencies += slf4jSimpleArtifact
  ).
  dependsOn(root)
