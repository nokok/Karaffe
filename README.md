<p align="center">
  <img src="https://avatars1.githubusercontent.com/u/10540388?s=200" alt="Karaffe logo" width="15%" />
</p>

---

# The Karaffe Programming Language

[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE) [![Build Status](https://travis-ci.org/nokok/Karaffe.svg?branch=v0.1.0-dev)](https://travis-ci.org/nokok/Karaffe) [![Build status](https://ci.appveyor.com/api/projects/status/smqreoar38kifhfs/branch/v0.1.0-dev?svg=true)](https://ci.appveyor.com/project/nokok/karaffe/branch/v0.1.0-dev) [![Maintainability](https://api.codeclimate.com/v1/badges/aa20a7b3efcbe8ebfc41/maintainability)](https://codeclimate.com/github/nokok/Karaffe/maintainability)

The Karaffe is a new programming language, **currently under active development.**

# Goals

- **Statically Typed** 
- **Object Oriented**

# Non-goals

TODO

## How to build

<!--
| Repo/Branch | Build Status |
|---|---|
| [`Karaffe/Karaffe/master`](https://github.com/Karaffe/Karaffe) | [![Build Status](https://travis-ci.org/Karaffe/Karaffe.svg?branch=master)](https://travis-ci.org/Karaffe/Karaffe) |
| [`nokok/Karaffe/v0.1.0-dev`](https://github.com/nokok/Karaffe/tree/v0.1.0-dev) | [![Build Status](https://travis-ci.org/nokok/Karaffe.svg?branch=v0.1.0-dev)](https://travis-ci.org/nokok/Karaffe) [![Build status](https://ci.appveyor.com/api/projects/status/smqreoar38kifhfs/branch/v0.1.0-dev?svg=true)](https://ci.appveyor.com/project/nokok/karaffe/branch/v0.1.0-dev) [![Maintainability](https://api.codeclimate.com/v1/badges/aa20a7b3efcbe8ebfc41/maintainability)](https://codeclimate.com/github/nokok/Karaffe/maintainability)| 
-->

### Requirements

[JDK 1.8+ required.](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### Building

The project is built with Gradle(with Gradle Wrapper). You can build the project and run the tests using the following command.

```
$ ./gradlew installDist
```

Run the compiler.

```
$ ./build/install/Karaffe-compiler/bin/krfc
```
