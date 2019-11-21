package io.github.sapientpants.structurizr.macros.views

import com.structurizr.Workspace
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DeploymentViewsTest {
    @Test
    fun `addToViews() does not add a deployment view when there are no DeploymentNodes`() {
        val workspace = Workspace("WorkspaceName", "WorkspaceDescription")
        val model = workspace.model
        val views = workspace.views
        val softwareSystem = model.addSoftwareSystem(
            "SoftwareSystemName",
            "SoftwareSystemDescription"
        )

        DeploymentViews.addToViews(softwareSystem, views)

        assertTrue(views.deploymentViews.isEmpty())
    }

    @Test
    fun `addToViews() adds a deployment view when there are DeploymentNodes`() {
        val workspace = Workspace("WorkspaceName", "WorkspaceDescription")
        val model = workspace.model
        val views = workspace.views
        val softwareSystem = model.addSoftwareSystem(
            "SoftwareSystemName",
            "SoftwareSystemDescription"
        )

        model.addDeploymentNode(
            "production",
            "DeploymentNodeName",
            "DeploymentNodeDescription",
            "DeploymentNodeTechnology"
        )

        DeploymentViews.addToViews(softwareSystem, views)

        assertEquals(1, views.deploymentViews.size)
    }

    @Test
    fun `addToViews() adds a deployment view per environment`() {
        val workspace = Workspace("WorkspaceName", "WorkspaceDescription")
        val model = workspace.model
        val views = workspace.views
        val softwareSystem = model.addSoftwareSystem(
            "SoftwareSystemName",
            "SoftwareSystemDescription"
        )

        model.addDeploymentNode(
            "development",
            "DeploymentNodeName",
            "DeploymentNodeDescription",
            "DeploymentNodeTechnology"
        )

        model.addDeploymentNode(
            "production",
            "DeploymentNodeName",
            "DeploymentNodeDescription",
            "DeploymentNodeTechnology"
        )

        DeploymentViews.addToViews(softwareSystem, views)

        assertEquals(2, views.deploymentViews.size)
    }
}
