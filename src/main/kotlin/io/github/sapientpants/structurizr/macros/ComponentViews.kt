package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Container
import com.structurizr.view.ViewSet

object ComponentViews {
    fun addToViews(containers: Set<Container>, views: ViewSet) {
        containers
            .filter { container -> !container.components.isEmpty() }
            .forEach { container ->
                val componentView = views.createComponentView(
                    container,
                    "${container.name} components",
                    "Components diagram for ${container.name}."
                )
                container.components
                    .forEach { component ->
                        componentView.add(component)
                        componentView.addNearestNeighbours(component)
                    }
            }
    }
}