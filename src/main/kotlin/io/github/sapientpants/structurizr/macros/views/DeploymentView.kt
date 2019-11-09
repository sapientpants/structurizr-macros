package io.github.sapientpants.structurizr.macros.views

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

/**
 * Macros to add deployment views.
 * @see com.structurizr.view.DeploymentView
 */
object DeploymentView {
    /**
     * Adds a deployment view to the provided set of views. The deployment view will include
     * all DeploymentNodes declared in the workspace to which the ViewSet belongs.
     * @params softwareSystem the system of interest
     * @params environment the environment of the deployment diagram (e.g. development, staging, production, etc.)
     * @params views the set of views to which the DeploymentView will be added
     * @see com.structurizr.model.DeploymentNode
     * @see com.structurizr.view.DeploymentView
     */
    fun addToViews(
        softwareSystem: SoftwareSystem,
        environment: String,
        views: ViewSet
    ) {
        val deploymentView = views.createDeploymentView(
            softwareSystem,
            "DeploymentView-$environment",
            "Deployment diagram for ${softwareSystem.name}"
        )
        deploymentView.environment = environment
        deploymentView.addAllDeploymentNodes()
    }
}
