package io.github.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.model.Enterprise

object StructurizrInitializer {
    fun init(workspaceName: String,
             workspaceDescription: String,
             enterpriseName: String): Workspace {
        val workspace = Workspace(workspaceName, workspaceDescription)

        val model = workspace.model
        model.enterprise = Enterprise(enterpriseName)

        return workspace
    }
}