package com.sapientpants.structurizr.macros

import com.structurizr.Workspace
import com.structurizr.api.StructurizrClient
import io.github.cdimascio.dotenv.Dotenv
import java.math.BigInteger

object StructurizrRenderer {
    fun render(workspace: Workspace) {
        val dotenv = Dotenv.load()
        val apiKey = dotenv["STRUCTURIZR_API_KEY"]
        val apiSecret = dotenv["STRUCTURIZR_API_SECRET"]
        val workspaceId: Long = BigInteger(dotenv["STRUCTURIZR_WORKSPACE_ID"]).longValueExact()
        val structurizrClient = StructurizrClient(apiKey, apiSecret)
        structurizrClient.putWorkspace(workspaceId, workspace)
    }
}