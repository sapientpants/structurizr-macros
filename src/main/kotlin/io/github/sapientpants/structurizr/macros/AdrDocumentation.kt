package io.github.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.documentation.AdrToolsImporter
import com.structurizr.model.SoftwareSystem
import java.io.File

object AdrDocumentation {
    fun addToWorkspace(
        workspace: Workspace,
        softwareSystem: SoftwareSystem,
        ardDirectoryPath: String
    ) {
        val adrDirectory = File(ardDirectoryPath)

        val adrToolsImporter = AdrToolsImporter(workspace, adrDirectory)
        adrToolsImporter.importArchitectureDecisionRecords(softwareSystem)
    }
}