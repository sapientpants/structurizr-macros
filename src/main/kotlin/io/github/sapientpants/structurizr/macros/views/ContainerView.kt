package io.github.sapientpants.structurizr.macros.views

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

/**
 * Macros to add container views.
 * @see com.structurizr.view.ContainerView
 */
object ContainerView {
    /**
     * Adds a container view to the set of views. The containers will be all of those declared
     * as part of the provided SoftwareSystem. If there are no containers in the provided
     * SoftwareSystem, a ContainerView will not be added to the set of views.
     * @params softwareSystem the system of interest
     * @params views the set of views to which the ContainerView will be added
     * @params tags the tags to use when filtering the containers
     * @see com.structurizr.view.ContainerView
     */
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet, tags: Set<String> = emptySet()) {
        val containers = Tags.filter(softwareSystem.containers, tags)
        if (containers.isEmpty()) {
            return
        }

        val tagLine = Views.tagLine(tags)
        val containerView = views.createContainerView(
            softwareSystem,
            "Containers-${softwareSystem.name}",
            "Container diagram for ${softwareSystem.name}$tagLine"
        )

        containers
            .forEach { container ->
                containerView.add(container)
                containerView.addNearestNeighbours(container)
            }
    }
}
