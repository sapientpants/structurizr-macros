package io.github.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object SystemContextView {
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        val systemContextView = views.createSystemContextView(
            softwareSystem,
            "SystemContext",
            "System context diagram for ${softwareSystem.name}"
        )
        systemContextView.isEnterpriseBoundaryVisible = false
        systemContextView.addNearestNeighbours(softwareSystem)
    }
}