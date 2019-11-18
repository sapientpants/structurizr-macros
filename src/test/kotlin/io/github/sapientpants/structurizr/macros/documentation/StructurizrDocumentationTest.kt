package io.github.sapientpants.structurizr.macros.documentation

import com.structurizr.Workspace
import com.structurizr.model.SoftwareSystem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StructurizrDocumentationTest {
    private val structurizrDirectoryPath = "./src/test/markdown/structurizr"
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
        Assertions.assertEquals(0, workspace!!.documentation.sections.size)

        StructurizrDocumentation.addToWorkspace(
            workspace!!,
            softwareSystem!!,
            structurizrDirectoryPath
        )

        Assertions.assertEquals(12, workspace!!.documentation.sections.size)
    }

    @Test
    fun `addToWorkspace() adds Arc42 documentation to the workspace when skipping ADs`() {
        Assertions.assertEquals(0, workspace!!.documentation.sections.size)

        StructurizrDocumentation.addToWorkspace(
            workspace!!,
            softwareSystem!!,
            structurizrDirectoryPath,
            skipDecisionLog = true
        )

        Assertions.assertEquals(11, workspace!!.documentation.sections.size)
    }
}
