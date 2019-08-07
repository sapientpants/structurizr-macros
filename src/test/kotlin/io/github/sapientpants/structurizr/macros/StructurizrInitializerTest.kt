package io.github.sapientpants.structurizr.macros

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class StructurizrInitializerTest {
    val enterpriseName = "My Enterprise"
    val workspaceName = "My Enterprise Workspace"
    val workspaceDescription = "My Enterprise Workspace description"

    @Test
    fun `init() creates an enterprise`() {
        val workspace = StructurizrInitializer.init(
            workspaceName, workspaceDescription, enterpriseName
        )
        val model = workspace.model
        val enterprise = model.enterprise
        assertNotNull(enterprise)
    }

    @Test
    fun `init() creates an enterprise with the expected name`() {
        val workspace = StructurizrInitializer.init(
            workspaceName, workspaceDescription, enterpriseName
        )
        val model = workspace.model
        val enterprise = model.enterprise
        assertEquals(enterpriseName, enterprise.name)
    }

    @Test
    fun `init() creates a workspace`() {
        val workspace = StructurizrInitializer.init(
            workspaceName, workspaceDescription, enterpriseName
        )
        assertNotNull(workspace)
    }

    @Test
    fun `init() creates a workspace with the expected name`() {
        val workspace = StructurizrInitializer.init(
            workspaceName, workspaceDescription, enterpriseName
        )
        assertEquals(workspace.name, workspaceName)
    }

    @Test
    fun `init() creates a workspace with the expected description`() {
        val workspace = StructurizrInitializer.init(
            workspaceName, workspaceDescription, enterpriseName
        )
        assertEquals(workspace.description, workspaceDescription)
    }
}