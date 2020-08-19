package io.github.sapientpants.structurizr.macros.renderer

import com.structurizr.Workspace
import com.structurizr.api.StructurizrClient
import com.structurizr.encryption.EncryptionStrategy
import io.github.sapientpants.structurizr.macros.Env
import java.math.BigInteger

/**
 * Macros to render the workspace using Structurizr.
 */
internal object StructurizrRenderer {
    /**
     * Renders the workspace using Structurizr. The API key, API secret and workspace are retrieved
     * using Env.getProperty()
     *
     * @params workspace the workspace to render
     * @see com.structurizr.api.StructurizrClient
     * @see Env.getProperty(String)
     */
    fun render(workspace: Workspace, encryptionStrategy: EncryptionStrategy?) {
        val apiKey = Env.getProperty("STRUCTURIZR_API_KEY")
        val apiSecret = Env.getProperty("STRUCTURIZR_API_SECRET")
        val workspaceId: Long =
            BigInteger(Env.getProperty("STRUCTURIZR_WORKSPACE_ID")).longValueExact()

        val structurizrClient = StructurizrClient(apiKey, apiSecret)
        structurizrClient.setEncryptionStrategy(encryptionStrategy)
        structurizrClient.putWorkspace(workspaceId, workspace)
    }
}
