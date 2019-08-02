package com.sapientpants.structurizr.macros

import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.Model
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ComponentView
import com.structurizr.view.ViewSet

object ComponentViews {
    const val DO_NOT_RENDER = "ComponentViews_DO NOT RENDER"

    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        softwareSystem.containers
            .filter { container -> !container.tags.contains(DO_NOT_RENDER) }
            .forEach { container ->
                val componentView = views.createComponentView(
                    container,
                    Utils.keyify("${container.name} components"),
                    "Components diagram for ${container.name}."
                )
                container.components
                    .forEach { component ->
                        componentView.add(component)
                        addRelatedContainers(
                            softwareSystem,
                            component,
                            componentView
                        )
                        addRelatedSoftwareSystems(
                            softwareSystem.model,
                            component,
                            componentView
                        )

                        component.relationships
                            .forEach { relationship ->
                                val other = if (relationship.destination == component) {
                                    relationship.source
                                } else {
                                    relationship.destination
                                }

                                when (other) {
                                    is Container -> componentView.add(other)
                                    is SoftwareSystem -> componentView.add(other)
                                }
                            }
                    }
            }
    }

    private fun addRelatedContainers(softwareSystem: SoftwareSystem, component: Component, view: ComponentView) {
        softwareSystem.containers
            .forEach { container ->
                container.relationships
                    .forEach { relationship ->
                        if (relationship.destination == component || relationship.source == component) {
                            view.add(container)
                        }
                    }
            }
    }

    private fun addRelatedSoftwareSystems(model: Model, component: Component, view: ComponentView) {
        model.softwareSystems
            .forEach { softwareSystem ->
                softwareSystem.relationships
                    .forEach { relationship ->
                        if (relationship.destination == component || relationship.source == component) {
                            view.add(softwareSystem)
                        }
                    }
            }
    }
}