package io.github.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object SystemLandscapeView {
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        val systemLandscapeView = views.createSystemLandscapeView(
            "SystemLandscape-${softwareSystem.name}",
            "System landscape for ${softwareSystem.name}"
        )
        systemLandscapeView.addAllElements()
    }
}