package io.github.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.documentation.Arc42DocumentationTemplate
import com.structurizr.model.SoftwareSystem
import java.io.File

/**
 * Macro to include Arc42 documentation in the workspace.
 *
 * Note: This will only work with the StructurizrRenderer
 *
 * @see StructurizrRenderer
 */
object Arc42Documentation {
    const val DEFAULT_ARC42_DIRECTORY_PATH = "./src/main/markdown/arc42"

    /**
     * Add Arc42 documentation to the workspace.
     * @params workspace the workspace to which the documentation is to be added
     * @params softwareSystem the system of interest
     * @see com.structurizr.documentation.Arc42DocumentationTemplate
     */
    fun addToWorkspace(
        workspace: Workspace,
        softwareSystem: SoftwareSystem
    ) {
        addToWorkspace(workspace, softwareSystem, DEFAULT_ARC42_DIRECTORY_PATH)
    }

    private fun addToWorkspace(
        workspace: Workspace,
        softwareSystem: SoftwareSystem,
        arc42DirectoryPath: String
    ) {
        val template = Arc42DocumentationTemplate(workspace)

        val documentationRoot = File(arc42DirectoryPath)
        template.addIntroductionAndGoalsSection(
            softwareSystem,
            File(documentationRoot, "01-introduction-and-goals.md")
        )
        template.addConstraintsSection(softwareSystem, File(documentationRoot, "02-architecture-constraints.md"))
        template.addContextAndScopeSection(
            softwareSystem,
            File(documentationRoot, "03-system-scope-and-context.md")
        )
        template.addSolutionStrategySection(softwareSystem, File(documentationRoot, "04-solution-strategy.md"))
        template.addBuildingBlockViewSection(softwareSystem, File(documentationRoot, "05-building-block-view.md"))
        template.addRuntimeViewSection(softwareSystem, File(documentationRoot, "06-runtime-view.md"))
        template.addDeploymentViewSection(softwareSystem, File(documentationRoot, "07-deployment-view.md"))
        template.addCrosscuttingConceptsSection(
            softwareSystem,
            File(documentationRoot, "08-crosscutting-concepts.md")
        )
        template.addArchitecturalDecisionsSection(
            softwareSystem,
            File(documentationRoot, "09-architecture-decisions.md")
        )
        template.addRisksAndTechnicalDebtSection(
            softwareSystem,
            File(documentationRoot, "10-quality-requirements.md")
        )
        template.addQualityRequirementsSection(
            softwareSystem,
            File(documentationRoot, "11-risks-and-technical-debt.md")
        )
        template.addGlossarySection(softwareSystem, File(documentationRoot, "12-glossary.md"))
    }
}