package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.documentation.AdrDocumentation
import io.github.sapientpants.structurizr.macros.documentation.Arc42Documentation
import io.github.sapientpants.structurizr.macros.views.ComponentViews
import io.github.sapientpants.structurizr.macros.views.ContainerView
import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.renderer.StructurizrRenderer
import io.github.sapientpants.structurizr.macros.Styling
import io.github.sapientpants.structurizr.macros.views.SystemContextView
import io.github.sapientpants.structurizr.macros.views.SystemLandscapeView
import io.github.sapientpants.structurizr.macros.Utils
import io.github.sapientpants.structurizr.macros.documentation.ArchitectureDocumentation
import io.github.sapientpants.structurizr.macros.documentation.StructurizrDocumentation
import io.github.sapientpants.structurizr.macros.documentation.ViewpointsAndPerspectivesDocumentation

object StructurizrBuilder {
    fun build(
        enterpriseName: String,
        workspaceName: String,
        workspaceDescription: String,
        architectureDocumentation: ArchitectureDocumentation = ArchitectureDocumentation.NONE,
        includeADR: Boolean = false,
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

        if (includeADR) {
            AdrDocumentation.addToWorkspace(workspace, softwareSystem)
        }

        when (architectureDocumentation) {
            ArchitectureDocumentation.ARC_42 ->
                Arc42Documentation.addToWorkspace(
                    workspace,
                    softwareSystem,
                    skipArchitectureDecisions = includeADR
                )

            ArchitectureDocumentation.NONE -> {
                // do nothing
            }

            ArchitectureDocumentation.STRUCTURIZR ->
                StructurizrDocumentation.addToWorkspace(
                    workspace,
                    softwareSystem,
                    skipDecisionLog = includeADR
                )

            ArchitectureDocumentation.VIEWPOINTS_AND_PERSPECTIVES ->
                ViewpointsAndPerspectivesDocumentation.addToWorkspace(workspace, softwareSystem)
        }

        // Apply the style
        Styling.applyStructurizrStyle(views)

        // Render the diagrams
        StructurizrRenderer.render(workspace)
    }
}