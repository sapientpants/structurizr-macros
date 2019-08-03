package io.github.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.api.StructurizrClient
import java.math.BigInteger

object StructurizrRenderer {
    fun render(workspace: Workspace) {
        val apiKey = Utils.getProperty("STRUCTURIZR_API_KEY")
        val apiSecret = Utils.getProperty("STRUCTURIZR_API_SECRET")
        val workspaceId: Long =
            BigInteger(Utils.getProperty("STRUCTURIZR_WORKSPACE_ID")).longValueExact()
        val structurizrClient = StructurizrClient(apiKey, apiSecret)
        structurizrClient.putWorkspace(workspaceId, workspace)
    }
}