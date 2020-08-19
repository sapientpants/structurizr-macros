package io.github.sapientpants.structurizr.macros.views

import com.structurizr.model.Container
import com.structurizr.model.Element
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

/**
 * Macros to add container context views to a set of views. A container context view is similar to a
 * system context view, except that the element of interest is a container rather than a software system.
 * Specifically this means that the diagram starts with a container and then adds the nearest neighbours
 * (aka immediate inbound and outbound dependencies) of the container to the diagram. This type of diagram
 * can be useful in situations where there are a large number of containers and it is desired to be able to
 * discuss the world from the perspective of a given container.
 * @see com.structurizr.view.ContainerView
 */
object CollaboratorViews {
    /**
     * Adds collaborator views for each of the supplied elements to a set of views.
     * @params elements the elements of interest
     * @params tags the tags to use to filter the given elements
     * @params views the set of views to which the ContainerViews will be added
     */
    fun <T : Element> addToViews(elements: Set<T>, views: ViewSet, tags: Set<String> = emptySet()) {
        val filteredElements = Tags.filter(elements, tags)
        filteredElements.forEach { element ->
            when (element) {
                is Container -> createContainerCollaboratorView(element, views)
            }
        }
    }

    private fun createContainerCollaboratorView(container: Container, views: ViewSet) {
        val view = views.createContainerView(
            container.softwareSystem,
            "Collaborators-${container.name}",
            "Container collaborator diagram for ${container.name}"
        )
        view.addNearestNeighbours(container)
    }
}
