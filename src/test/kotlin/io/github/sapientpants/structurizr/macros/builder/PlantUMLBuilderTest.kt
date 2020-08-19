package io.github.sapientpants.structurizr.macros.builder

import io.github.sapientpants.structurizr.macros.Tags
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class PlantUMLBuilderTest {
    private val ENTERPRISE_NAME = "TestEnterprise"
    private val WORKSPACE_NAME = "Test"
    private val WORKSPACE_DESCRIPTION = "structurizr-macros test"

    private var outputPath: Path? = null

    @BeforeEach
    fun setUp() {
        outputPath = Files.createTempDirectory("plant-uml-builder-test-")
    }

    @Test
    fun `buildAndRender() should create a system context diagram`() {
        PlantUMLBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).outputPath(outputPath.toString())
            .buildAndRender() { model, _ ->
                model.addSoftwareSystem("TestSoftwareSystem", null)
            }

        assertTrue(
            File(
                outputPath.toString(),
                "TestSoftwareSystem_SystemContext.png"
            ).exists()
        )
    }

    @Test
    fun `buildAndRender() should create one system context diagram per system of interest`() {
        PlantUMLBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).outputPath(outputPath.toString())
            .buildAndRender() { model, _ ->
                val ss1 = model.addSoftwareSystem("TestSoftwareSystem1", null)
                ss1.addTags(Tags.SYSTEM_OF_INTEREST)

                val ss2 = model.addSoftwareSystem("TestSoftwareSystem2", null)
                ss2.addTags(Tags.SYSTEM_OF_INTEREST)
            }

        assertTrue(
            File(
                outputPath.toString(),
                "TestSoftwareSystem1_SystemContext.png"
            ).exists()
        )

        assertTrue(
            File(
                outputPath.toString(),
                "TestSoftwareSystem2_SystemContext.png"
            ).exists()
        )
    }

    @Test
    fun `buildAndRender() should create a container diagram`() {
        PlantUMLBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).outputPath(outputPath.toString())
            .buildAndRender() { model, _ ->
                val softwareSystem =
                    model.addSoftwareSystem(
                        "TestSoftwareSystem",
                        null
                    )
                softwareSystem.addContainer(
                    "TestContainer",
                    null,
                    null
                )
            }

        assertTrue(
            File(
                outputPath.toString(),
                "TestSoftwareSystem_Container.png"
            ).exists()
        )
    }

    @Test
    fun `buildAndRender() should create a component diagram`() {
        PlantUMLBuilder(
            ENTERPRISE_NAME,
            WORKSPACE_NAME,
            WORKSPACE_DESCRIPTION
        ).outputPath(outputPath.toString())
            .buildAndRender() { model, _ ->
                val softwareSystem =
                    model.addSoftwareSystem(
                        "TestSoftwareSystem",
                        null
                    )
                val container =
                    softwareSystem.addContainer(
                        "TestContainer",
                        null,
                        null
                    )
                container.addComponent(
                    "TestComponent",
                    null,
                    null
                )
            }

        assertTrue(
            File(
                outputPath.toString(),
                "TestSoftwareSystem_TestContainer_Component.png"
            ).exists()
        )
    }
}
