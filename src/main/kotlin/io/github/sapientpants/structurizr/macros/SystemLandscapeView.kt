package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Enterprise
import com.structurizr.view.ViewSet

object SystemLandscapeView {
    fun addToViews(enterprise: Enterprise, views: ViewSet) {
        val systemLandscapeView = views.createSystemLandscapeView(
            "SystemLandscape-${enterprise.name}",
            "System landscape diagram for ${enterprise.name}"
        )
        systemLandscapeView.addAllElements()
    }
}