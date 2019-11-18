package io.github.sapientpants.structurizr.macros.documentation

import com.structurizr.Workspace
import com.structurizr.model.SoftwareSystem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Arc42DocumentationTest {
    private val arc42DirectoryPath = "./src/test/markdown/arc42"
    private var softwareSystem: SoftwareSystem? = null
    private var workspace: Workspace? = null

    @BeforeEach
    fun setUp() {
        workspace = Workspace("WorkspaceName", "WorkspaceDescription")
        softwareSystem =
            workspace!!.model.addSoftwareSystem(
                "SoftwareSystemName",
                "SoftwareSystemDescription"
            )
    }

    @Test
    fun `addToWorkspace() adds Arc42 documentation to the workspace`() {
        assertEquals(0, workspace!!.documentation.sections.size)

        Arc42Documentation.addToWorkspace(workspace!!, softwareSystem!!, arc42DirectoryPath)

        assertEquals(12, workspace!!.documentation.sections.size)
    }

    @Test
    fun `addToWorkspace() adds Arc42 documentation to the workspace when skipping ADs`() {
        assertEquals(0, workspace!!.documentation.sections.size)

        Arc42Documentation.addToWorkspace(
            workspace!!,
            softwareSystem!!,
            arc42DirectoryPath,
            skipArchitectureDecisions = true
        )

        assertEquals(11, workspace!!.documentation.sections.size)
    }
}
