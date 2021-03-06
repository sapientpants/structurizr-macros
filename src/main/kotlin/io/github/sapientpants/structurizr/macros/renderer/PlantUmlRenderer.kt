package io.github.sapientpants.structurizr.macros.renderer

import com.structurizr.Workspace
import com.structurizr.io.plantuml.PlantUMLWriter
import com.structurizr.view.View
import net.sourceforge.plantuml.FileUtils
import net.sourceforge.plantuml.SourceFileReader
import net.sourceforge.plantuml.security.SFile
import java.io.File
import java.io.FileWriter
import java.io.StringWriter

/**
 * Macros to render the workspace using PlantUML.
 */
internal object PlantUmlRenderer {
    /**
     * Renders the workspace using PlantUML to a set of PNG files.
     *
     * @params workspace the workspace to render
     * @params outputDirectoryPath the path to the directory where the PNG images will be written
     */
    fun render(workspace: Workspace, outputDirectoryPath: String) {
        val views = workspace.views

        File(outputDirectoryPath).mkdirs()

        views.systemLandscapeViews.forEach { view ->
            renderView(view, outputDirectoryPath)
        }

        views.systemContextViews.forEach { view ->
            renderView(view, outputDirectoryPath)
        }

        views.containerViews.forEach { view ->
            renderView(view, outputDirectoryPath)
        }

        views.componentViews.forEach { view ->
            renderView(view, outputDirectoryPath)
        }

        views.deploymentViews.forEach { view ->
            renderView(view, outputDirectoryPath)
        }
    }

    private fun renderView(
        view: View,
        outputDirectoryPath: String
    ) {
        val plantUMLWriter = PlantUMLWriter()
        val viewPlantUML = StringWriter()
        plantUMLWriter.write(view, viewPlantUML)

        val plantUmlFile = File.createTempFile("plant-uml-renderer-", ".puml")
        val fileWriter = FileWriter(plantUmlFile)
        fileWriter.write(viewPlantUML.toString())
        fileWriter.close()

        val filename = Renderer.filenamize(view.key, "png")

        val sourceFileReader = SourceFileReader(plantUmlFile)
        val image = sourceFileReader.generatedImages.first()
        val outputFile = SFile("$outputDirectoryPath/$filename")
        FileUtils.copyToFile(SFile.fromFile(image.pngFile), outputFile)
    }
}
