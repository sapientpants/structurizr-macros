# structurizr-macros

[![Build Status](https://travis-ci.org/sapientpants/structurizr-macros.svg?branch=master)](https://travis-ci.org/sapientpants/structurizr-macros)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=coverage)](https://sonarcloud.io/dashboard?id=structurizr-macros)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=security_rating)](https://sonarcloud.io/dashboard?id=structurizr-macros)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=sqale_index)](https://sonarcloud.io/dashboard?id=structurizr-macros)

Macros (or procedures if you prefer) to make it more convenient to create Structurizr diagrams in a consistent manner.

## Installation

### Gradle

Add the dependency to `build.gradle.kts`

```kotlin
implementation("io.github.sapientpants:structurizr-macros:0.0.17-SNAPSHOT")
```

### Maven

Add the dependency to `pom.xml`

```xml
<dependency>
    <groupId>io.github.sapientpants</groupId>
    <artifactId>structurizr-macros</artifactId>
    <version>0.0.17-SNAPSHOT</version>
</dependency>
```

## Usage

### Architecture Decision Records

`brew install adr-tools`

`adr-init ./src/main/markdown/adr`

### Arc42 Templates

`mkdir -p ./src/main/markdown/arc42`

Copy the templates from [Structurizr](https://github.com/structurizr/java/tree/master/structurizr-examples/src/com/structurizr/example/documentation/arc42/markdown)

## License

MIT © [Marc Tremblay](https://github.com/sapientpants)
