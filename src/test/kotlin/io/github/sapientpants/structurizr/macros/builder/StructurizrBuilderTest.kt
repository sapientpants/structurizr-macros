package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.Tags
import io.github.sapientpants.structurizr.macros.documentation.ArchitectureDocumentation
import java.lang.IllegalStateException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
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
    fun `build() should create one system context diagram per system of interest`() {
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

    @Test
    fun `build() should add Arc42 documentation to the workspace`() {
        val workspace = StructurizrBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).architectureDocumentation(ArchitectureDocumentation.ARC_42)
            .documentationSourcePath("./src/test/markdown/arc42")
            .build { model, _ ->
                model.addSoftwareSystem("TestSoftwareSystem", null)
        }

        assertFalse(workspace.documentation.isEmpty)
    }

    @Test
    fun `build() throws IllegalStateException with Arc42 documentation and missing documentationSourcePath`() {
        assertThrows(IllegalStateException::class.java) { ->
            val workspace = StructurizrBuilder(
                ENTERPRISE_NAME,
                WORKSPACE_NAME,
                WORKSPACE_DESCRIPTION
            ).architectureDocumentation(ArchitectureDocumentation.ARC_42)
                .build { model, _ ->
                    model.addSoftwareSystem("TestSoftwareSystem", null)
                }
        }
    }

    @Test
    fun `build() should add Structurizr documentation to the workspace`() {
        val workspace = StructurizrBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).architectureDocumentation(ArchitectureDocumentation.STRUCTURIZR)
            .documentationSourcePath("./src/test/markdown/structurizr")
            .build { model, _ ->
                model.addSoftwareSystem("TestSoftwareSystem", null)
            }

        assertFalse(workspace.documentation.isEmpty)
    }

    @Test
    fun `build() throws IllegalStateException with Structurizr documentation and missing documentationSourcePath`() {
        assertThrows(IllegalStateException::class.java) { ->
            val workspace = StructurizrBuilder(
                ENTERPRISE_NAME,
                WORKSPACE_NAME,
                WORKSPACE_DESCRIPTION
            ).architectureDocumentation(ArchitectureDocumentation.STRUCTURIZR)
                .build { model, _ ->
                    model.addSoftwareSystem("TestSoftwareSystem", null)
                }
        }
    }

    @Test
    fun `build() should add Viewpoints and Perspectives documentation to the workspace`() {
        val workspace = StructurizrBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).architectureDocumentation(ArchitectureDocumentation.VIEWPOINTS_AND_PERSPECTIVES)
            .documentationSourcePath("./src/test/markdown/viewpointsandperspectives")
            .build { model, _ ->
                model.addSoftwareSystem("TestSoftwareSystem", null)
            }

        assertFalse(workspace.documentation.isEmpty)
    }

    @Test
    fun `build() throws IllegalStateException with Viewpoints and Perspectives documentation and missing documentationSourcePath`() {
        assertThrows(IllegalStateException::class.java) { ->
            val workspace = StructurizrBuilder(
                ENTERPRISE_NAME,
                WORKSPACE_NAME,
                WORKSPACE_DESCRIPTION
            ).architectureDocumentation(ArchitectureDocumentation.VIEWPOINTS_AND_PERSPECTIVES)
                .build { model, _ ->
                    model.addSoftwareSystem("TestSoftwareSystem", null)
                }
        }
    }

    @Test
    fun `build() should add ADR documentation to the workspace`() {
        val workspace = StructurizrBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).adrSourcePath("./src/test/markdown/adr")
            .build { model, _ ->
                model.addSoftwareSystem("TestSoftwareSystem", null)
            }

        assertFalse(workspace.documentation.decisions.isEmpty())
    }
}
