package io.github.sapientpants.structurizr.macros

import com.structurizr.view.ViewSet

object SystemLandscapeView {
    fun addToViews(key: String, description: String, views: ViewSet) {
        val contextView = views.createSystemLandscapeView(key, description)
        contextView.addAllElements()
    }
}