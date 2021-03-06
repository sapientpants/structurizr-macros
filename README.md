# structurizr-macros

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.sapientpants/structurizr-macros/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.sapientpants/structurizr-macros)
[![Build Status](https://travis-ci.com/sapientpants/structurizr-macros.svg?branch=trunk)](https://travis-ci.com/sapientpants/structurizr-macros)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=alert_status)](https://sonarcloud.io/dashboard?id=structurizr-macros)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=coverage)](https://sonarcloud.io/dashboard?id=structurizr-macros)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=bugs)](https://sonarcloud.io/dashboard?id=structurizr-macros)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=sqale_index)](https://sonarcloud.io/dashboard?id=structurizr-macros)

Macros (or procedures if you prefer) to make it more convenient to create Structurizr diagrams in a consistent manner.

## Installation

### Java

Java >= 8 and <= 12

```sh
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk-12
```

### Gradle

Add the dependency to `build.gradle.kts`

```kotlin
implementation("io.github.sapientpants:structurizr-macros:VERSION")
```

### Maven

Add the dependency to `pom.xml`

```xml
<dependency>
    <groupId>io.github.sapientpants</groupId>
    <artifactId>structurizr-macros</artifactId>
    <version>VERSION</version>
</dependency>
```

## Usage

### Architecture Documentation

- [Arc42](doc/architecture-documentation/arc42.md)
- [Architecture Decision Records](doc/architecture-documentation/architecture-decision-records.md)
- [Structurizr](doc/architecture-documentation/structurizr.md)
- [Viewpoints and Perspectives](doc/architecture-documentation/viewpoints-and-perspectices.md)

### Rendering

- [PlantUML](doc/rendering/plantuml.md)
- [Structurizr](doc/rendering/structurizr.md)


### Examples

See [https://github.com/sapientpants/structurizr-macros-examples](https://github.com/sapientpants/structurizr-macros-examples)

## License

MIT © [Marc Tremblay](https://github.com/sapientpants)
