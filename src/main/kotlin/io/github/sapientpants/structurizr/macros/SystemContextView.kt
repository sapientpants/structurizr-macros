package io.github.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

/**
 * Macros to add system context views.
 * @see com.structurizr.view.SystemContextView
 */
object SystemContextView {
    /**
     * Adds a system context view to the workspace.
     * @params softwareSystem the software system of interest
     * @params views the set of views to which the SystemContextView will be added
     */
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