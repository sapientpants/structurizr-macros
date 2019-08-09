package io.github.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.model.Enterprise

/**
 * Initializers for a Structurizr workspace.
 *
 * @see com.structurizr.Workspace
 */
object StructurizrInitializer {
    /**
     * Initializes a Structurizr workspace, model and enterprise.
     *
     * @param workspaceName the name of the workspace
     * @param workspaceDescription the description of the workspace
     * @param enterpriseName the name of the enterprise
     * @return an initialized workspace
     * @see com.structurizr.model.Enterprise
     * @see com.structurizr.model.Model
     * @see com.structurizr.Workspace
     */
    fun init(
        workspaceName: String,
        workspaceDescription: String,
        enterpriseName: String
    ): Workspace {
        val workspace = Workspace(workspaceName, workspaceDescription)

        val model = workspace.model
        model.enterprise = Enterprise(enterpriseName)

        return workspace
    }
}