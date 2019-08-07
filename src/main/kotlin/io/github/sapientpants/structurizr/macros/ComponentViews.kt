package io.github.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object ComponentViews {
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        softwareSystem.containers
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