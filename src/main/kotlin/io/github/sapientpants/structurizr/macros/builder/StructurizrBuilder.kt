package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.documentation.AdrDocumentation
import io.github.sapientpants.structurizr.macros.documentation.Arc42Documentation
import io.github.sapientpants.structurizr.macros.documentation.ArchitectureDocumentation
import io.github.sapientpants.structurizr.macros.documentation.StructurizrDocumentation
import io.github.sapientpants.structurizr.macros.documentation.ViewpointsAndPerspectivesDocumentation
import io.github.sapientpants.structurizr.macros.renderer.StructurizrRenderer
import io.github.sapientpants.structurizr.macros.styles.StructurizrStyle
import io.github.sapientpants.structurizr.macros.styles.Style
import io.github.sapientpants.structurizr.macros.views.ComponentViews
import io.github.sapientpants.structurizr.macros.views.ContainerView
import io.github.sapientpants.structurizr.macros.views.DeploymentViews
import io.github.sapientpants.structurizr.macros.views.SystemContextView
import io.github.sapientpants.structurizr.macros.views.SystemLandscapeView

object StructurizrBuilder {
    fun build(
        enterpriseName: String,
        workspaceName: String,
        workspaceDescription: String,
        addImplicitRelationships: Boolean = true,
        architectureDocumentation: ArchitectureDocumentation = ArchitectureDocumentation.NONE,
        includeADR: Boolean = false,
        style: Style = StructurizrStyle(),
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

        if (includeADR) {
            AdrDocumentation.addToWorkspace(workspace, systemOfInterest)
        }

        when (architectureDocumentation) {
            ArchitectureDocumentation.ARC_42 ->
                Arc42Documentation.addToWorkspace(
                    workspace,
                    systemOfInterest,
                    skipArchitectureDecisions = includeADR
                )

            ArchitectureDocumentation.NONE -> {
                // do nothing
            }

            ArchitectureDocumentation.STRUCTURIZR ->
                StructurizrDocumentation.addToWorkspace(
                    workspace,
                    systemOfInterest,
                    skipDecisionLog = includeADR
                )

            ArchitectureDocumentation.VIEWPOINTS_AND_PERSPECTIVES ->
                ViewpointsAndPerspectivesDocumentation.addToWorkspace(workspace, systemOfInterest)
        }

        // Apply the style
        style.applyToViews(views)

        // Render the diagrams
        StructurizrRenderer.render(workspace)
    }
}
