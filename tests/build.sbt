name := s"${(name in Global).value}-tests"

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.13.2",
  "org.scalatest"  %% "scalatest"  % "3.0.0"
)
