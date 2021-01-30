# Stubborn

> Stubborn: having or showing dogged determination not to change one's attitude
> or position on something.

[![CircleCI](https://circleci.com/gh/salesforce/stubborn.svg?style=svg)](https://circleci.com/gh/salesforce/stubborn)

A scala library for performing retries on actions that might fail.

This project is migrated from https://github.com/krux/stubborn for all versions
and previous commit history, please go to https://github.com/krux/stubborn

## Configuration

Add the Sonatype.org Releases repo as a resolver in your `build.sbt` or `Build.scala` as appropriate.

Add stubborn as a dependency in your `build.sbt` or `Build.scala` as appropriate.

```scala
libraryDependencies ++= Seq(
  // Other dependencies ...
  "com.krux" %% "stubborn" % "3.0.0"
)
```

## Scala Versions

This project is compiled, tested, and published for the following Scala versions:

1. 2.13.x
2. 2.12.x

## Usage

See examples in `examples` [directory](https://github.com/krux/stubborn/tree/master/examples).

## License

Stubborn is licensed under [APL 2.0](LICENSE).
