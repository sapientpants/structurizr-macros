package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.renderer.PlantUmlRenderer
import io.github.sapientpants.structurizr.macros.styles.PlantUMLStyle
import io.github.sapientpants.structurizr.macros.styles.Style

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

        Builder.finalizeModelAndAddViewsToWorkspace(workspace, addImplicitRelationships, style)

        // Render the diagrams
        PlantUmlRenderer.render(workspace, outputPath)
    }
}
