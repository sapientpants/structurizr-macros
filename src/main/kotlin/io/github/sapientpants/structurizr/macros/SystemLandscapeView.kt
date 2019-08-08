package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Enterprise
import com.structurizr.view.ViewSet

/**
 * Macros to add system landscape views.
 * @see com.structurizr.view.SystemLandscapeView
 */
object SystemLandscapeView {
    /**
     * Adds a system landscape view to the workspace.
     * @params enterprise the enterprise to use
     * @params views the set of views
     */
    fun addToViews(enterprise: Enterprise, views: ViewSet) {
        val systemLandscapeView = views.createSystemLandscapeView(
            "SystemLandscape-${enterprise.name}",
            "System landscape diagram for ${enterprise.name}"
        )
        systemLandscapeView.addAllElements()
    }
}