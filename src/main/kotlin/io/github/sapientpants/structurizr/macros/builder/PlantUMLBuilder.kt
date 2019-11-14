package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.renderer.PlantUmlRenderer
import io.github.sapientpants.structurizr.macros.styles.PlantUMLStyle
import io.github.sapientpants.structurizr.macros.styles.Style
import io.github.sapientpants.structurizr.macros.views.ComponentViews
import io.github.sapientpants.structurizr.macros.views.ContainerView
import io.github.sapientpants.structurizr.macros.views.DeploymentViews
import io.github.sapientpants.structurizr.macros.views.SystemContextView
import io.github.sapientpants.structurizr.macros.views.SystemLandscapeView

object PlantUMLBuilder {
    private const val DEFAULT_OUTPUT_PATH = "./build/plantuml"

    fun build(
        enterpriseName: String,
        workspaceName: String,
        workspaceDescription: String,
        outputPath: String = DEFAULT_OUTPUT_PATH,
        addImplicitRelationships: Boolean = true,
        style: Style = PlantUMLStyle(),
        modelAndViewsBuilder: ModelAndViewsBuilder
    ) {
        val workspace = StructurizrInitializer.init(
            workspaceName,
            workspaceDescription,
            enterpriseName
        )
        val model = workspace.model
        val views = workspace.views

        modelAndViewsBuilder(model, views)

        if (addImplicitRelationships) {
            model.addImplicitRelationships()
        }

        // Declare the diagrams to render

        val systemOfInterest = BuilderUtils.systemOfInterest(model)

        SystemLandscapeView.addToViews(model, views)

        SystemContextView.addToViews(systemOfInterest, views)

        ContainerView.addToViews(systemOfInterest, views)

        ComponentViews.addToViews(systemOfInterest.containers, views)

        DeploymentViews.addToViews(systemOfInterest, views)

        // Apply the style
        style.applyToViews(views)

        // Render the diagrams
        PlantUmlRenderer.render(workspace, outputPath)
    }
}
