package io.github.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object ContainerViews {
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        if (softwareSystem.containers.isEmpty()) {
            return
        }

        val containerView = views.createContainerView(
            softwareSystem,
            "Containers",
            "Container diagram for ${softwareSystem.name}"
        )
        containerView.addAllPeople()
        containerView.addAllSoftwareSystems()
        containerView.addAllContainers()
    }
}