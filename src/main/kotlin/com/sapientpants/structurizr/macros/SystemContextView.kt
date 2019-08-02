package com.sapientpants.structurizr.macros

import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet

object SystemContextView {
    fun addToViews(softwareSystem: SoftwareSystem, views: ViewSet) {
        val contextView = views.createSystemContextView(
            softwareSystem,
            "SystemContext",
            "System Context diagram for ${softwareSystem.name}"
        )
        contextView.addAllSoftwareSystems()
        contextView.addAllPeople()
    }
}