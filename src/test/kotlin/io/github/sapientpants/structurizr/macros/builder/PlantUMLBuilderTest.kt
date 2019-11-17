package io.github.sapientpants.structurizr.macros.builder

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
    fun `build() should create a system context diagram`() {
        PlantUMLBuilder.build(
                ENTERPRISE_NAME,
                WORKSPACE_NAME,
                WORKSPACE_DESCRIPTION,
                outputPath.toString()
        ) { model, _ ->
            model.addSoftwareSystem("TestSoftwareSystem", null)
        }

        assertTrue(
                File(
                        outputPath.toString(),
                        "SystemContext_TestSoftwareSystem.png"
                ).exists()
        )
    }

    @Test
    fun `build() should create a container diagram`() {
        PlantUMLBuilder.build(
                ENTERPRISE_NAME,
                WORKSPACE_NAME,
                WORKSPACE_DESCRIPTION,
                outputPath.toString()
        ) { model, _ ->
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
                        "Containers_TestSoftwareSystem.png"
                ).exists()
        )
    }

    @Test
    fun `build() should create a component diagram`() {
        PlantUMLBuilder.build(
                ENTERPRISE_NAME,
                WORKSPACE_NAME,
                WORKSPACE_DESCRIPTION,
                outputPath.toString()
        ) { model, _ ->
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
                        "Components_TestContainer_TestSoftwareSystem.png"
                ).exists()
        )
    }
}
