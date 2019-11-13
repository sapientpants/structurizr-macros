# structurizr-macros

[![Build Status](https://travis-ci.org/sapientpants/structurizr-macros.svg?branch=master)](https://travis-ci.org/sapientpants/structurizr-macros)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=coverage)](https://sonarcloud.io/dashboard?id=structurizr-macros)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=security_rating)](https://sonarcloud.io/dashboard?id=structurizr-macros)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=structurizr-macros&metric=sqale_index)](https://sonarcloud.io/dashboard?id=structurizr-macros)

Macros (or procedures if you prefer) to make it more convenient to create Structurizr diagrams in a consistent manner.

## Installation

### Java

Java >= 8 and <= 12

```sh
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk-12
```

### Graphviz

Required if you will be rendering diagrams using PlantUML.

`brew install graphviz`

### Gradle

Add the dependency to `build.gradle.kts`

```kotlin
implementation("io.github.sapientpants:structurizr-macros:0.0.27")
```

### Maven

Add the dependency to `pom.xml`

```xml
<dependency>
    <groupId>io.github.sapientpants</groupId>
    <artifactId>structurizr-macros</artifactId>
    <version>0.0.27</version>
</dependency>
```

### Architecture Decision Records

If you want to use ADRs, then install adr-tools. Only works with the StructurizrRenderer.

`brew install adr-tools`

`adr-init ./src/main/markdown/adr`

### Arc42 Templates

If you want to use Arc42, then install the Arc42 templates. Only works with the StructurizrRenderer.

`mkdir -p ./src/main/markdown/arc42`

Copy the templates from [Structurizr](https://github.com/structurizr/java/tree/master/structurizr-examples/src/com/structurizr/example/documentation/arc42/markdown)

### Arc42 Templates

If you want to use the Arc42 architecture documentation format, then install the templates. Only works with the StructurizrRenderer.

`mkdir -p ./src/main/markdown/arc42`

Copy the templates from [Structurizr](https://github.com/structurizr/java/tree/master/structurizr-examples/src/com/structurizr/example/documentation/arc42/markdown)

### Structurizr Templates

If you want to use the Structurizr architecture documentation format, then install the templates. Only works with the StructurizrRenderer.

`mkdir -p ./src/main/markdown/structurizr`

Copy the templates from [Structurizr](https://github.com/structurizr/java/tree/master/structurizr-examples/src/com/structurizr/example/documentation/structurizr/markdown)

### Viewpoints and Perspectives Templates

If you want to use the Viewpoints and Perspectives architecture documentation format, then install the templates. Only works with the StructurizrRenderer.

`mkdir -p ./src/main/markdown/viewpointsandperspectives`

Copy the templates from [Structurizr](https://github.com/structurizr/java/tree/master/structurizr-examples/src/com/structurizr/example/documentation/viewpointsandperspectives/markdown)

## Usage

### Examples

See [https://github.com/sapientpants/structurizr-macros-examples](https://github.com/sapientpants/structurizr-macros-examples)

## License

MIT © [Marc Tremblay](https://github.com/sapientpants)
