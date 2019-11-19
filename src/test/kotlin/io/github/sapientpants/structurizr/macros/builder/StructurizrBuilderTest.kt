package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.Tags
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StructurizrBuilderTest {
    private val ENTERPRISE_NAME = "TestEnterprise"
    private val WORKSPACE_NAME = "Test"
    private val WORKSPACE_DESCRIPTION = "structurizr-macros test"

    @Test
    fun `build() should create a system context diagram`() {
        val workspace = StructurizrBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).build { model, _ ->
            model.addSoftwareSystem("TestSoftwareSystem", null)
        }

        assertEquals(1, workspace.views.systemContextViews.size)
    }

    @Test
    fun `buildAndRender() should create one system context diagram per system of interest`() {
        val workspace = StructurizrBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).build { model, _ ->
            val ss1 = model.addSoftwareSystem("TestSoftwareSystem1", null)
            ss1.addTags(Tags.SYSTEM_OF_INTEREST)

            val ss2 = model.addSoftwareSystem("TestSoftwareSystem2", null)
            ss2.addTags(Tags.SYSTEM_OF_INTEREST)
        }

        assertEquals(2, workspace.views.systemContextViews.size)
    }
}
