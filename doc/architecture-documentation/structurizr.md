# Structurizr Templates

## Caveats

This will only work when rendering using Structurizr

## Steps

If you want to use the Structurizr architecture documentation format, then install the templates. Only works with the StructurizrRenderer.

`mkdir -p ./src/main/markdown/structurizr`

Copy the templates from [Structurizr](https://github.com/structurizr/java/tree/master/structurizr-examples/src/com/structurizr/example/documentation/structurizr/markdown)

## Code example

```kotlin
StructurizrBuilder(
    enterpriseName,
    workspaceName,
    workspaceDescription
).architectureDocumentation(ArchitectureDocumentation.STRUCTURIZR)
 .buildAndRender { model, views ->

     // TODO: define the model and views

}
```