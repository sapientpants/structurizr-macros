package io.github.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object ContainerView {
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        if (softwareSystem.containers.isEmpty()) {
            return
        }

        val containerView = views.createContainerView(
            softwareSystem,
            "Containers",
            "Container diagram for ${softwareSystem.name}"
        )
        softwareSystem.containers
            .forEach { container ->
                containerView.add(container)
                containerView.addNearestNeighbours(container)
            }
    }
}