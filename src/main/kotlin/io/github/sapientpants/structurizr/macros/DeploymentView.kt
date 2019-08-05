package io.github.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object DeploymentView {
    fun addToViews(
        softwareSystem: SoftwareSystem,
        environment: String,
        views: ViewSet
    ) {
        val deploymentView = views.createDeploymentView(
            softwareSystem,
            "DeploymentView-${environment}",
            "Deployment diagram for ${softwareSystem.name}"
        )
        deploymentView.environment = environment
        deploymentView.addAllDeploymentNodes()
    }
}