package io.github.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object DeploymentView {
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        val contextView = views.createDeploymentView(
            softwareSystem,
            "SystemContext",
            "Deployment diagram for ${softwareSystem.name}"
        )
        contextView.addAllDeploymentNodes()
    }
}