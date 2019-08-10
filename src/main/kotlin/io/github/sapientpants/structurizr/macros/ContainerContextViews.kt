package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Container
import com.structurizr.view.ViewSet

/**
 * Macros to add container context views to a set of views. A container context view is similar to a
 * system context view, except that the element of interest is a container rather than a software system.
 * Specifically this means that the diagram starts with a container and then adds the nearest neighbours
 * (aka immediate inbound and outbound dependencies) of the container to the diagram. This type of diagram
 * can be useful in situations where there are a large number of containers and it is desired to be able to
 * discuss the world from the perspective of a given container.
 * @see com.structurizr.view.ContainerView
 */
object ContainerContextViews {
    /**
     * Adds a container context view for each of the supplied containers to a set of views.
     * @params containers the containers of interest
     * @params views the set of views to which the ContainerViews will be added
     */
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