package io.github.sapientpants.structurizr.macros.views

import com.structurizr.model.DeploymentNode
import com.structurizr.model.Model
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

/**
 * Macros to add deployment views.
 * @see com.structurizr.view.DeploymentView
 */
object DeploymentViews {
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
        views: ViewSet
    ) {
        val environments = findUniqueDeploymentEnvironments(softwareSystem.model)
        environments.forEach { environment ->
            addToViews(softwareSystem, views, environment)
        }
    }

    private fun findUniqueDeploymentEnvironments(model: Model): Set<String> {
        tailrec fun go(deploymentNodes: Set<DeploymentNode>, acc: Set<String>): Set<String> {
            if (deploymentNodes.isEmpty()) {
                return acc
            }

            val x = deploymentNodes.first()
            val xs = deploymentNodes.subtract(setOf(x))

            return go(xs, acc + x.environment)
        }

        return go(model.deploymentNodes, emptySet())
    }

    private fun addToViews(
        softwareSystem: SoftwareSystem,
        views: ViewSet,
        environment: String
    ) {
        val deploymentView = views.createDeploymentView(
            softwareSystem,
            "DeploymentView-$environment",
            "Deployment diagram for ${softwareSystem.name}"
        )
        deploymentView.environment = environment

        val model = softwareSystem.model
        val deploymentNodes = model.deploymentNodes

        deploymentNodes.forEach { deploymentNode ->
            if (environment == deploymentNode.environment) {
                deploymentView.add(deploymentNode)
            }
        }
    }
}
