package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.Workspace
import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.documentation.AdrDocumentation
import io.github.sapientpants.structurizr.macros.documentation.Arc42Documentation
import io.github.sapientpants.structurizr.macros.documentation.ArchitectureDocumentation
import io.github.sapientpants.structurizr.macros.documentation.StructurizrDocumentation
import io.github.sapientpants.structurizr.macros.documentation.ViewpointsAndPerspectivesDocumentation
import io.github.sapientpants.structurizr.macros.renderer.StructurizrRenderer
import io.github.sapientpants.structurizr.macros.styles.StructurizrStyle
import io.github.sapientpants.structurizr.macros.styles.Style

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

        Builder.finalizeModelAndAddViewsToWorkspace(workspace, addImplicitRelationships, style)

        addArchitectureDocumentationToWorkspace(workspace, includeADR, architectureDocumentation)

        // Render the diagrams
        StructurizrRenderer.render(workspace)
    }

    private fun addArchitectureDocumentationToWorkspace(
        workspace: Workspace,
        includeADR: Boolean,
        architectureDocumentation: ArchitectureDocumentation
    ) {
        val model = workspace.model
        val systemOfInterest = Builder.systemsOfInterest(model).first()

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
    }
}
