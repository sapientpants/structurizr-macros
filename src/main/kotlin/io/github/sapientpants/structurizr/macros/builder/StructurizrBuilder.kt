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

class StructurizrBuilder(
    enterpriseName: String,
    workspaceName: String,
    workspaceDescription: String
) : Builder(enterpriseName, workspaceName, workspaceDescription) {

    private var addImplicitRelationships = true
    private var architectureDocumentation = ArchitectureDocumentation.NONE
    private var includeADR = false
    private var style: Style = StructurizrStyle()

    fun addImplicitRelationships(addImplicitRelationships: Boolean): StructurizrBuilder {
        this.addImplicitRelationships = addImplicitRelationships
        return this
    }

    fun architectureDocumentation(
        architectureDocumentation: ArchitectureDocumentation
    ): StructurizrBuilder {
        this.architectureDocumentation = architectureDocumentation
        return this
    }

    fun includeADR(includeADR: Boolean): StructurizrBuilder {
        this.includeADR = includeADR
        return this
    }

    fun style(style: Style): StructurizrBuilder {
        this.style = style
        return this
    }

    fun build(
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

        finalizeModelAndAddViewsToWorkspace(workspace, addImplicitRelationships, style)

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
        val systemOfInterest = systemsOfInterest(model).first()

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
