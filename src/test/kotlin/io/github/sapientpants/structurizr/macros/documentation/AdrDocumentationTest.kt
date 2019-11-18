package io.github.sapientpants.structurizr.macros.documentation

import com.structurizr.Workspace
import com.structurizr.model.SoftwareSystem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AdrDocumentationTest {
    private val adrDirectoryPath = "./src/test/markdown/adr"
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
    fun `addToWorkspace() adds ADR decisions to workspace`() {
        assertEquals(0, workspace!!.documentation.decisions.size)

        AdrDocumentation.addToWorkspace(workspace!!, softwareSystem!!, adrDirectoryPath)

        assertEquals(1, workspace!!.documentation.decisions.size)
    }
}
