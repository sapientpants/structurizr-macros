package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.Element
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object NearestNeighboursView {
    fun addToViews(elements: Set<Element>, views: ViewSet) {
        elements.forEach { element ->
            when (element) {
                is SoftwareSystem -> addToViews(element, views)
                is Container -> addToViews(element, views)
                is Component -> addToViews(element, views)
            }
        }
    }

    private fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        val view = views.createSystemContextView(
            softwareSystem,
            "NearestNeighbour-SoftwareSystem-${softwareSystem.name}",
            "Nearest neighbors of ${softwareSystem.name}"
        )
        view.addNearestNeighbours(softwareSystem)
    }

    private fun addToViews(container: Container, views: ViewSet) {
        val view = views.createContainerView(
            container.softwareSystem,
            "NearestNeighbour-Container-${container.name}",
            "Nearest neighbors of ${container.name}"
        )
        view.addNearestNeighbours(container)
    }

    private fun addToViews(component: Component, views: ViewSet) {
        val view = views.createComponentView(
            component.container,
            "NearestNeighbour-Component-${component.name}",
            "Nearest neighbors of ${component.name}"
        )
        view.addNearestNeighbours(component)
    }
}