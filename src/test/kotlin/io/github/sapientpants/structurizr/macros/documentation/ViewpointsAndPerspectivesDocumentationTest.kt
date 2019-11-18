package io.github.sapientpants.structurizr.macros.documentation

import com.structurizr.Workspace
import com.structurizr.model.SoftwareSystem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ViewpointsAndPerspectivesDocumentationTest {
    private val viewpointsAndPerspectivesDirectoryPath =
        "./src/test/markdown/viewpointsandperspectives"
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

        ViewpointsAndPerspectivesDocumentation.addToWorkspace(
            workspace!!,
            softwareSystem!!,
            viewpointsAndPerspectivesDirectoryPath
        )

        Assertions.assertEquals(7, workspace!!.documentation.sections.size)
    }
}
