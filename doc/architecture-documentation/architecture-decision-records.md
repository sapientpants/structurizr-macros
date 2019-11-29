# Architecture Decision Records

## Caveats

This will only work when rendering using Structurizr

## Steps

If you want to use ADRs, then install adr-tools. Only works with the StructurizrRenderer.

`brew install adr-tools`

`adr-init ./src/main/markdown/adr`

## Code example

```kotlin
StructurizrBuilder(
    enterpriseName,
    workspaceName,
    workspaceDescription
).adrSourcePath("./src/main/markdown/adr")
 .buildAndRender { model, views ->

     // TODO: define the model and views

}
```