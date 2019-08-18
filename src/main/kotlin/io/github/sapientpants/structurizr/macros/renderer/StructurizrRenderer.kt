package io.github.sapientpants.structurizr.macros.renderer

import com.structurizr.Workspace
import com.structurizr.api.StructurizrClient
import io.github.sapientpants.structurizr.macros.Utils
import java.math.BigInteger

/**
 * Macros to render the workspace using Structurizr.
 */
object StructurizrRenderer {
    /**
     * Renders the workspace using Structurizr. The API key, API secret and workspace are retrieved
     * using Utils.getProperty()
     *
     * @params workspace the workspace to render
     * @see com.structurizr.api.StructurizrClient
     * @see Utils.getProperty(String)
     */
    fun render(workspace: Workspace) {
        val apiKey = Utils.getProperty("STRUCTURIZR_API_KEY")
        val apiSecret = Utils.getProperty("STRUCTURIZR_API_SECRET")
        val workspaceId: Long =
            BigInteger(Utils.getProperty("STRUCTURIZR_WORKSPACE_ID")).longValueExact()
        val structurizrClient = StructurizrClient(apiKey, apiSecret)
        structurizrClient.putWorkspace(workspaceId, workspace)
    }
}