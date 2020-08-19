package io.github.sapientpants.structurizr.macros.documentation

import com.structurizr.Workspace
import com.structurizr.documentation.ViewpointsAndPerspectivesDocumentationTemplate
import com.structurizr.model.SoftwareSystem
import java.io.File

/**
 * Macro to include Viewpoints and Perspectives documentation in the workspace.
 *
 * Note: This will only work with the StructurizrRenderer
 *
 * @see StructurizrRenderer
 */
internal object ViewpointsAndPerspectivesDocumentation {
    private const val DEFAULT_SOURCE_PATH = "./src/main/markdown/viewpointsandperspectives"

    /**
     * Add Viewpoints and Perspectives documentation to the workspace.
     * @params workspace the workspace to which the documentation is to be added
     * @params softwareSystem the system of interest
     * @see com.structurizr.documentation.ViewpointsAndPerspectivesDocumentationTemplate
     */
    fun addToWorkspace(
        workspace: Workspace,
        softwareSystem: SoftwareSystem,
        sourcePath: String = DEFAULT_SOURCE_PATH
    ) {
        val template = ViewpointsAndPerspectivesDocumentationTemplate(workspace)

        val documentationRoot = File(sourcePath)
        template.addIntroductionSection(softwareSystem, File(documentationRoot, "01-introduction.md"))
        template.addGlossarySection(softwareSystem, File(documentationRoot, "02-glossary.md"))
        template.addSystemStakeholdersAndRequirementsSection(
            softwareSystem,
            File(documentationRoot, "03-system-stakeholders-and-requirements.md")
        )
        template.addArchitecturalForcesSection(softwareSystem, File(documentationRoot, "04-architectural-forces.md"))
        template.addArchitecturalViewsSection(softwareSystem, File(documentationRoot, "05-architectural-views"))
        template.addSystemQualitiesSection(softwareSystem, File(documentationRoot, "06-system-qualities.md"))
        template.addAppendicesSection(softwareSystem, File(documentationRoot, "07-appendices.md"))
    }
}
