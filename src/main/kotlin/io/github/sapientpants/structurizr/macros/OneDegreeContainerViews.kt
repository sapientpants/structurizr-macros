package io.github.sapientpants.structurizr.macros

import com.structurizr.model.*
import com.structurizr.view.ContainerView
import com.structurizr.view.View
import com.structurizr.view.ViewSet

object OneDegreeContainerViews {
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        val model = softwareSystem.model

        softwareSystem.containers.forEach { container ->
            val containerView = views.createContainerView(
                softwareSystem,
                "${container.name} Dependents and Dependencies",
                "The world according to the ${container.name}"
            )
            containerView.add(container)
            addUpstreamContainersToView(
                softwareSystem,
                container,
                containerView
            )
            addDownstreamContainersToView(
                softwareSystem,
                container,
                containerView
            )
            addUpstreamSoftwareSystemsToView(
                model,
                softwareSystem,
                container,
                containerView
            )
            addDownstreamSoftwareSystemsToView(
                model,
                softwareSystem,
                container,
                containerView
            )
        }
    }

    private fun addUpstreamContainersToView(softwareSystem: SoftwareSystem, reference: Container, view: ContainerView) {
        softwareSystem.containers.forEach { container ->
            container.relationships.forEach { relationship ->
                if (relationship.destination == reference) {
                    addElementToView(
                        relationship.source,
                        view
                    )
                }
            }
        }
    }

    private fun addDownstreamContainersToView(
        softwareSystem: SoftwareSystem, reference: Container, view: ContainerView,
        deep: Boolean = false
    ) {
        softwareSystem.containers.forEach { container ->
            container.relationships.forEach { relationship ->
                if (relationship.source == reference) {
                    val destination = relationship.destination
                    addElementToView(
                        destination,
                        view
                    )
                    if (deep) {
                        when (destination) {
                            is Container -> addDownstreamContainersToView(
                                softwareSystem,
                                destination,
                                view
                            )
                        }
                    }
                }
            }
        }
    }

    private fun addUpstreamSoftwareSystemsToView(
        model: Model, softwareSystem: SoftwareSystem,
        reference: Container, view: ContainerView
    ) {
        model.softwareSystems.forEach { s ->
            if (s != softwareSystem) {
                s.relationships.forEach { relationship ->
                    if (relationship.destination == reference) {
                        val source = relationship.source
                        addElementToView(source, view)
                    }
                }
            }
        }
    }

    private fun addDownstreamSoftwareSystemsToView(
        model: Model, softwareSystem: SoftwareSystem,
        reference: Container, view: ContainerView
    ) {
        model.softwareSystems.forEach { s ->
            if (s != softwareSystem) {
                s.relationships.forEach { relationship ->
                    if (relationship.source == reference) {
                        val destination = relationship.destination
                        addElementToView(
                            destination,
                            view
                        )
                    }
                }
            }
        }
    }

    private fun addElementToView(element: Element, view: View) {
        when (view) {
            is ContainerView ->
                when (element) {
                    is Container -> view.add(element)
                    is Person -> view.add(element)
                    is SoftwareSystem -> view.add(element)
                }
        }
    }
}