package io.github.sapientpants.structurizr.macros.documentation

import com.structurizr.Workspace
import com.structurizr.documentation.AdrToolsImporter
import com.structurizr.model.SoftwareSystem
import java.io.File

/**
 * Macro to include ADR documentation in the workspace.
 *
 * Note: This will only work with the StructurizrRenderer
 *
 * @see StructurizrRenderer
 */
internal object AdrDocumentation {
    private const val DEFAULT_ADR_DIRECTORY_PATH = "./src/main/markdown/adr"

    /**
     * Add ADR documentation to the workspace.
     * @params workspace the workspace to which the documentation is to be added
     * @params softwareSystem the system of interest
     * @see com.structurizr.documentation.AdrToolsImporter
     */
    fun addToWorkspace(
        workspace: Workspace,
        softwareSystem: SoftwareSystem,
        adrDirectoryPath: String = DEFAULT_ADR_DIRECTORY_PATH
    ) {
        val adrDirectory = File(adrDirectoryPath)

        val adrToolsImporter = AdrToolsImporter(workspace, adrDirectory)
        adrToolsImporter.importArchitectureDecisionRecords(softwareSystem)
    }
}
