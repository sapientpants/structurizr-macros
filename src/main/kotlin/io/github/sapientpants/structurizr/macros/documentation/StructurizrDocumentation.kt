package io.github.sapientpants.structurizr.macros.documentation

import com.structurizr.Workspace
import com.structurizr.documentation.StructurizrDocumentationTemplate
import com.structurizr.model.SoftwareSystem
import java.io.File

/**
 * Macro to include Software Guidebook documentation in the workspace.
 *
 * Note: This will only work with the StructurizrRenderer
 *
 * @see StructurizrRenderer
 */
object StructurizrDocumentation {
    private const val DEFAULT_SOURCE_PATH = "./src/main/markdown/structurizr"

    /**
     * Add Software Guidebook documentation to the workspace.
     * @params workspace the workspace to which the documentation is to be added
     * @params softwareSystem the system of interest
     * @see com.structurizr.documentation.StructurizrDocumentationTemplate
     */
    fun addToWorkspace(
        workspace: Workspace,
        softwareSystem: SoftwareSystem,
        sourcePath: String = DEFAULT_SOURCE_PATH,
        skipDecisionLog: Boolean = false
    ) {
        val template = StructurizrDocumentationTemplate(workspace)

        val documentationRoot = File(sourcePath)
        template.addContextSection(softwareSystem, File(documentationRoot, "01-context.md"))
        template.addFunctionalOverviewSection(softwareSystem, File(documentationRoot, "02-functional-overview.md"))
        template.addQualityAttributesSection(softwareSystem, File(documentationRoot, "03-quality-attributes.md"))
        template.addConstraintsSection(softwareSystem, File(documentationRoot, "04-constraints.md"))
        template.addPrinciplesSection(softwareSystem, File(documentationRoot, "05-principles.md"))
        template.addSoftwareArchitectureSection(softwareSystem, File(documentationRoot, "06-software-architecture.md"))
        template.addDataSection(softwareSystem, File(documentationRoot, "07-data.md"))
        template.addInfrastructureArchitectureSection(
            softwareSystem,
            File(documentationRoot, "08-infrastructure-architecture.md")
        )
        template.addDeploymentSection(softwareSystem, File(documentationRoot, "09-deployment.md"))
        template.addDevelopmentEnvironmentSection(
            softwareSystem,
            File(documentationRoot, "10-development-environment.md")
        )
        template.addOperationAndSupportSection(softwareSystem, File(documentationRoot, "11-operation-and-support.md"))
        if (!skipDecisionLog) {
            template.addDecisionLogSection(softwareSystem, File(documentationRoot, "12-decision-log.md"))
        }
    }
}