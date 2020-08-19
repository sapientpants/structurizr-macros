package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.Workspace
import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.renderer.PlantUmlRenderer
import io.github.sapientpants.structurizr.macros.styles.PlantUMLStyle

public class PlantUMLBuilder(
    enterpriseName: String,
    workspaceName: String,
    workspaceDescription: String
) : Builder(enterpriseName, workspaceName, workspaceDescription) {

    private val defaultOutputPath = "./build/plantuml"

    private var outputPath = defaultOutputPath

    init {
        this.style(PlantUMLStyle())
    }

    public fun outputPath(outputPath: String): PlantUMLBuilder {
        this.outputPath = outputPath
        return this
    }

    override fun build(
        modelAndViewsBuilder: ModelAndViewsBuilder
    ): Workspace {
        val workspace = StructurizrInitializer.init(
            workspaceName,
            workspaceDescription,
            enterpriseName
        )
        val model = workspace.model
        val views = workspace.views

        modelAndViewsBuilder(model, views)

        finalizeModelAndAddViewsToWorkspace(workspace, style!!)

        return workspace
    }

    override fun render(workspace: Workspace) {
        PlantUmlRenderer.render(workspace, outputPath)
    }
}
