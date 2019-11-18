package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.renderer.PlantUmlRenderer
import io.github.sapientpants.structurizr.macros.styles.PlantUMLStyle
import io.github.sapientpants.structurizr.macros.styles.Style

class PlantUMLBuilder(
    enterpriseName: String,
    workspaceName: String,
    workspaceDescription: String
) : Builder(enterpriseName, workspaceName, workspaceDescription) {

    private val defaultOutputPath = "./build/plantuml"

    private var outputPath = defaultOutputPath
    private var addImplicitRelationships = true
    private var style: Style = PlantUMLStyle()

    fun outputPath(outputPath: String): PlantUMLBuilder {
        this.outputPath = outputPath
        return this
    }

    fun addImplicitRelationships(addImplicitRelationships: Boolean): PlantUMLBuilder {
        this.addImplicitRelationships = addImplicitRelationships
        return this
    }

    fun style(style: Style): PlantUMLBuilder {
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

        // Render the diagrams
        PlantUmlRenderer.render(workspace, outputPath)
    }
}
