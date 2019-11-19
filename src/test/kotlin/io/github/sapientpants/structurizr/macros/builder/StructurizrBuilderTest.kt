package io.github.sapientpants.structurizr.macros.builder

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StructurizrBuilderTest {
    private val ENTERPRISE_NAME = "TestEnterprise"
    private val WORKSPACE_NAME = "Test"
    private val WORKSPACE_DESCRIPTION = "structurizr-macros test"

    @Test
    fun `build() should create a system context diagram`() {
        val workspace = PlantUMLBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).build() { model, _ ->
            model.addSoftwareSystem("TestSoftwareSystem", null)
        }

        assertEquals(1, workspace.views.systemContextViews.size)
    }
}
