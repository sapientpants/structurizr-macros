package io.github.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.documentation.AdrToolsImporter
import com.structurizr.model.SoftwareSystem
import java.io.File

object AdrDocumentation {
    fun addToWorkspace(workspace: Workspace, softwareSystem: SoftwareSystem) {
        val adrDirectory = File("./src/main/markdown/adr")

        val adrToolsImporter = AdrToolsImporter(workspace, adrDirectory)
        adrToolsImporter.importArchitectureDecisionRecords(softwareSystem)
    }
}