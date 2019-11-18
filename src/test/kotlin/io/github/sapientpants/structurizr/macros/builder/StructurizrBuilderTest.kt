package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.documentation.ArchitectureDocumentation
import io.github.sapientpants.structurizr.macros.styles.StructurizrStyle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StructurizrBuilderTest {
    private val workspaceName = "WorkspaceName"
    private val workspaceDescription = "WorkspaceDescription"
    private val enterpriseName = "EnterpriseName"
    private val softwareSystemName = "SoftwareSystemName"
    private val softwareSystemDescription = "SoftwareSystemDescription"

    @Test
    fun `prepareWorkspace() creates the expected workspace`() {
        val workspace = StructurizrBuilder.initializeWorkspace(
            enterpriseName,
            workspaceName,
            workspaceDescription,
            true,
            ArchitectureDocumentation.NONE,
            false,
            StructurizrStyle()
        ) { model, _ ->
            model.addSoftwareSystem(softwareSystemName, softwareSystemDescription)
        }

        assertEquals(workspaceName, workspace.name)
        assertEquals(workspaceDescription, workspace.description)
        assertEquals(enterpriseName, workspace.model.enterprise.name)
    }
}
