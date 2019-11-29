# Rendering with Structurizr

## Structurizer parameters

The following parameters can be passed in to the StructurizrBuilder as environment variables, system properties or in a
.env file

```sh
STRUCTURIZR_API_KEY
STRUCTURIZR_API_SECRET
STRUCTURIZR_WORKSPACE_ID
```

## Code example

```kotlin
StructurizrBuilder(
    enterpriseName,
    workspaceName,
    workspaceDescription
).buildAndRender { model, views ->

     // TODO: define the model and views

}
```