package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Model
import com.structurizr.view.ViewSet

/**
 * Macros to add system landscape views.
 * @see com.structurizr.view.SystemLandscapeView
 */
object SystemLandscapeView {
    /**
     * Adds a system landscape view to the workspace. If there is only one software system
     * in the provided model, then a system landscape diagram will not be added.
     * @params model the model of the system of interest
     * @params views the set of views to which the SystemLandscapeView will be added
     */
    fun addToViews(model: Model, views: ViewSet) {
        if (model.softwareSystems.size <= 1) {
            return
        }

        val enterprise = model.enterprise
        val systemLandscapeView = views.createSystemLandscapeView(
            "SystemLandscape-${enterprise.name}",
            "System landscape diagram for ${enterprise.name}"
        )
        systemLandscapeView.addAllElements()
    }
}