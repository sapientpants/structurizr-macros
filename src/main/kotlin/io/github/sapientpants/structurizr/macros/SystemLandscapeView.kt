package io.github.sapientpants.structurizr.macros

import com.structurizr.view.ViewSet

object SystemLandscapeView {
    fun addToViews(key: String, description: String, views: ViewSet) {
        val systemLandscapeView = views.createSystemLandscapeView(key, description)
        systemLandscapeView.addAllElements()
    }
}