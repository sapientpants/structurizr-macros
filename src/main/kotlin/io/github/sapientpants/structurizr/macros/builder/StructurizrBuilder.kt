package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.Workspace
import com.structurizr.encryption.EncryptionStrategy
import io.github.sapientpants.structurizr.macros.StructurizrInitializer
import io.github.sapientpants.structurizr.macros.documentation.AdrDocumentation
import io.github.sapientpants.structurizr.macros.documentation.Arc42Documentation
import io.github.sapientpants.structurizr.macros.documentation.ArchitectureDocumentation
import io.github.sapientpants.structurizr.macros.documentation.StructurizrDocumentation
import io.github.sapientpants.structurizr.macros.documentation.ViewpointsAndPerspectivesDocumentation
import io.github.sapientpants.structurizr.macros.renderer.StructurizrRenderer
import io.github.sapientpants.structurizr.macros.styles.StructurizrStyle

private const val DOCUMENTATION_SOURCE_PATH_MUST_NOT_BE_NULL =
    "documentationSourcePath must not be null"

class StructurizrBuilder(
    enterpriseName: String,
    workspaceName: String,
    workspaceDescription: String
) : Builder(enterpriseName, workspaceName, workspaceDescription) {

    private var adrSourcePath: String? = null
    private var architectureDocumentation = ArchitectureDocumentation.NONE
    private var documentationSourcePath: String? = null
    private var encryptionStrategy: EncryptionStrategy? = null

    init {
        this.style(StructurizrStyle())
    }

    fun adrSourcePath(
        adrSourcePath: String
    ): StructurizrBuilder {
        this.adrSourcePath = adrSourcePath
        return this
    }

    fun architectureDocumentation(
        architectureDocumentation: ArchitectureDocumentation
    ): StructurizrBuilder {
        this.architectureDocumentation = architectureDocumentation
        return this
    }

    fun documentationSourcePath(
        documentationSourcePath: String
    ): StructurizrBuilder {
        this.documentationSourcePath = documentationSourcePath
        return this
    }

    fun encryptionStrategy(encryptionStrategy: EncryptionStrategy): StructurizrBuilder {
        this.encryptionStrategy = encryptionStrategy
        return this
    }

    override fun build(
        modelAndViewsBuilder: ModelAndViewsBuilder
    ): Workspace {
        val workspace = StructurizrInitializer.init(
            workspaceName,
            workspaceDescription,
            enterpriseName
        )
        val model = workspace.model
        val views = workspace.views

        modelAndViewsBuilder(model, views)

        finalizeModelAndAddViewsToWorkspace(workspace, style!!)

        addArchitectureDocumentationToWorkspace(workspace, architectureDocumentation)

        return workspace
    }

    override fun render(workspace: Workspace) {
        StructurizrRenderer.render(workspace, encryptionStrategy)
    }

    private fun addArchitectureDocumentationToWorkspace(
        workspace: Workspace,
        architectureDocumentation: ArchitectureDocumentation
    ) {
        val model = workspace.model
        val systemOfInterest = systemsOfInterest(model).first()

        if (adrSourcePath != null) {
            AdrDocumentation.addToWorkspace(
                workspace,
                systemOfInterest,
                adrSourcePath!!
            )
        }

        when (architectureDocumentation) {
            ArchitectureDocumentation.ARC_42 -> {
                check(documentationSourcePath != null) {
                    DOCUMENTATION_SOURCE_PATH_MUST_NOT_BE_NULL
                }

                Arc42Documentation.addToWorkspace(
                    workspace,
                    systemOfInterest,
                    documentationSourcePath!!
                )
            }

            ArchitectureDocumentation.NONE -> {
                // do nothing
            }

            ArchitectureDocumentation.STRUCTURIZR -> {
                check(documentationSourcePath != null) {
                    DOCUMENTATION_SOURCE_PATH_MUST_NOT_BE_NULL
                }

                StructurizrDocumentation.addToWorkspace(
                    workspace,
                    systemOfInterest,
                    documentationSourcePath!!
                )
            }

            ArchitectureDocumentation.VIEWPOINTS_AND_PERSPECTIVES -> {
                check(documentationSourcePath != null) {
                    DOCUMENTATION_SOURCE_PATH_MUST_NOT_BE_NULL
                }

                ViewpointsAndPerspectivesDocumentation.addToWorkspace(
                    workspace,
                    systemOfInterest,
                    documentationSourcePath!!
                )
            }
        }
    }
}
