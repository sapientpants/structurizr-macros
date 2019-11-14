package io.github.sapientpants.structurizr.macros.views

import com.structurizr.model.Container
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Utils

/**
 * Macros to add container views.
 * @see com.structurizr.view.ComponentView
 */
object ComponentViews {
    /**
     * Adds a component view for each component of each container to the set of views.
     * The components will be all of those declared as part of the provided containers.
     * If there are no components in a provided container, a component view will not be
     * added to the set of views.
     * @params containers the containers whose components are of interest
     * @params views the set of views to which the ComponentViews will be added
     * @params tags the tags to use when filtering the components
     * @see com.structurizr.view.ComponentView
     */
    fun addToViews(containers: Set<Container>, views: ViewSet, tags: Set<String> = emptySet()) {
        val tagLine = Utils.tagLine(tags)
        containers
            .filter { container -> container.components.isNotEmpty() }
            .forEach { container ->
                val componentView = views.createComponentView(
                    container,
                    "${container.name} components",
                    "Components diagram for ${container.name}$tagLine."
                )
                val components = Utils.filter(container.components, tags)
                components
                    .forEach { component ->
                        componentView.add(component)
                        componentView.addNearestNeighbours(component)
                    }
            }
    }
}
