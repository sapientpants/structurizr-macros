package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.Tags
import io.github.sapientpants.structurizr.macros.Utils
import io.github.sapientpants.structurizr.macros.renderer.PlantUmlRenderer
import io.github.sapientpants.structurizr.macros.styles.PlantUMLStyle
import io.github.sapientpants.structurizr.macros.styles.Style
import io.github.sapientpants.structurizr.macros.views.ComponentViews
import io.github.sapientpants.structurizr.macros.views.ContainerView
import io.github.sapientpants.structurizr.macros.views.SystemContextView
import io.github.sapientpants.structurizr.macros.views.SystemLandscapeView

object PlantUMLBuilder {
    private const val DEFAULT_OUTPUT_PATH = "./build/plantuml"

    fun build(
        enterpriseName: String,
        workspaceName: String,
        workspaceDescription: String,
        modelBuilder: ModelBuilder,
        style: Style = PlantUMLStyle()
    ) {
        build(
            enterpriseName,
            workspaceName,
            workspaceDescription,
            DEFAULT_OUTPUT_PATH,
            modelBuilder,
            style
        )
    }

    fun build(
        enterpriseName: String,
        workspaceName: String,
        workspaceDescription: String,
        outputPath: String,
        modelBuilder: ModelBuilder,
        style: Style = PlantUMLStyle()
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
                setOf(Tags.SYSTEM_OF_INTEREST)
            ).firstOrNull()
                ?: return

        SystemLandscapeView.addToViews(model, views)

        SystemContextView.addToViews(softwareSystem, views)

        ContainerView.addToViews(softwareSystem, views)

        ComponentViews.addToViews(softwareSystem.containers, views)

        // Apply the style
        style.applyToViews(views)

        // Render the diagrams
        PlantUmlRenderer.render(workspace, outputPath)
    }
}
