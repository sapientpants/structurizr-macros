package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Container
import com.structurizr.view.ViewSet

object ContainerContextViews {
    fun addToViews(containers: Set<Container>, views: ViewSet) {
        containers.forEach { container ->
            val view = views.createContainerView(
                container.softwareSystem,
                "ContainerContext-${container.name}",
                "Container context diagram for ${container.name}"
            )
            view.addNearestNeighbours(container)
        }
    }
}