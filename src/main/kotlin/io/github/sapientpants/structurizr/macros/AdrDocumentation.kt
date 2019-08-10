package io.github.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.documentation.AdrToolsImporter
import com.structurizr.model.SoftwareSystem
import java.io.File

object AdrDocumentation {
    private const val DEFAULT_ARD_DIRECTORY_PATH = "./src/main/markdown/adr"

    fun addToWorkspace(
        workspace: Workspace,
        softwareSystem: SoftwareSystem
    ) {
        addToWorkspace(workspace, softwareSystem, DEFAULT_ARD_DIRECTORY_PATH)
    }

    private fun addToWorkspace(
        workspace: Workspace,
        softwareSystem: SoftwareSystem,
        ardDirectoryPath: String
    ) {
        val adrDirectory = File(ardDirectoryPath)

        val adrToolsImporter = AdrToolsImporter(workspace, adrDirectory)
        adrToolsImporter.importArchitectureDecisionRecords(softwareSystem)
    }
}