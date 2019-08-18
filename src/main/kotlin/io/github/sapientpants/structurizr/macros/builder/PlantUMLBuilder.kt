package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.views.ComponentViews
import io.github.sapientpants.structurizr.macros.views.ContainerView
import io.github.sapientpants.structurizr.macros.renderer.PlantUmlRenderer
import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.Styling
import io.github.sapientpants.structurizr.macros.views.SystemContextView
import io.github.sapientpants.structurizr.macros.views.SystemLandscapeView
import io.github.sapientpants.structurizr.macros.Utils

object PlantUMLBuilder {
    private const val DEFAULT_OUTPUT_PATH = "./build/plantuml"

    fun build(
        enterpriseName: String,
        workspaceName: String,
        workspaceDescription: String,
        modelBuilder: ModelBuilder
    ) {
        build(enterpriseName, workspaceName, workspaceDescription, DEFAULT_OUTPUT_PATH, modelBuilder)
    }

    fun build(
        enterpriseName: String,
        workspaceName: String,
        workspaceDescription: String,
        outputPath: String,
        modelBuilder: ModelBuilder
    ) {
        val workspace = StructurizrInitializer.init(
            workspaceName,
            workspaceDescription,
            enterpriseName
        )
        val model = workspace.model
        val views = workspace.views

        modelBuilder(model)

        model.addImplicitRelationships()

        // Declare the diagrams to render

        val softwareSystem =
            Utils.filter(
                model.softwareSystems,
                setOf(Styling.SYSTEM_OF_INTEREST_TAG)
            ).first()

        SystemLandscapeView.addToViews(model, views)

        SystemContextView.addToViews(softwareSystem, views)

        ContainerView.addToViews(softwareSystem, views)

        ComponentViews.addToViews(softwareSystem.containers, views)

        // Apply the style
        Styling.applyPlantUMLStyling(views)

        // Render the diagrams
        PlantUmlRenderer.render(workspace, outputPath)
    }
}