# Rendering with PlantUML

## Dependencies

### Graphviz

`brew install graphviz`

## Code example

```kotlin
PlantUMLBuilder(
    enterpriseName,
    workspaceName,
    workspaceDescription
).outputPath("./build/plantuml")
 .buildAndRender { model, views  ->

     // TODO: define the model and views

}
```