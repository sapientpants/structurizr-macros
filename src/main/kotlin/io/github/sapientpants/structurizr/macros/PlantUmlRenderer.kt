package io.github.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.io.plantuml.PlantUMLWriter
import net.sourceforge.plantuml.FileUtils
import net.sourceforge.plantuml.SourceFileReader
import java.io.File
import java.io.FileWriter
import java.io.StringWriter
import java.io.Writer

/**
 * Macros to render the workspace using PlantUML.
 */
object PlantUmlRenderer {
    /**
     * Renders the workspace using PlantUML to a set of PNG files.
     *
     * @params workspace the workspace to render
     * @params outputDirectoryPath the path to the directory where the PNG images will be written
     */
    fun render(workspace: Workspace, outputDirectoryPath: String) {
        val writer = StringWriter()
        render(workspace, writer)

        val plantUmlFile = File.createTempFile("plant-uml-renderer-", ".puml")
        val fileWriter = FileWriter(plantUmlFile)
        fileWriter.write(writer.toString())
        fileWriter.close()

        val sourceFileReader = SourceFileReader(plantUmlFile)
        sourceFileReader.generatedImages
            .forEach { image ->
                val outputFile = File(outputDirectoryPath + "/" + image.pngFile.name)
                FileUtils.copyToFile(image.pngFile, outputFile)
            }
    }

    /**
     * Renders the workspace using PlantUML to the supplied Writer.
     *
     * @params workspace the workspace to render
     * @params writer where to write the rendered PlantUML text
     */
    private fun render(workspace: Workspace, writer: Writer) {
        val plantUmlWriter = PlantUMLWriter()

        plantUmlWriter.write(workspace, writer)
    }
}