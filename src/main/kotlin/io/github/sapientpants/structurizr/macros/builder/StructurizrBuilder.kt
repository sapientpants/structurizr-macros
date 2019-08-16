package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.AdrDocumentation
import io.github.sapientpants.structurizr.macros.Arc42Documentation
import io.github.sapientpants.structurizr.macros.ComponentViews
import io.github.sapientpants.structurizr.macros.ContainerView
import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.StructurizrRenderer
import io.github.sapientpants.structurizr.macros.Styling
import io.github.sapientpants.structurizr.macros.SystemContextView
import io.github.sapientpants.structurizr.macros.SystemLandscapeView
import io.github.sapientpants.structurizr.macros.Utils

object StructurizrBuilder {
    fun build(
        enterpriseName: String,
        workspaceName: String,
        workspaceDescription: String,
        includeAdr: Boolean = false,
        includeArc42: Boolean = false,
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

        if (includeAdr) {
            AdrDocumentation.addToWorkspace(workspace, softwareSystem)
        }

        if (includeArc42) {
            Arc42Documentation.addToWorkspace(workspace, softwareSystem)
        }

        // Apply the style
        Styling.applyStructurizrStyle(views)

        // Render the diagrams
        StructurizrRenderer.render(workspace)
    }
}