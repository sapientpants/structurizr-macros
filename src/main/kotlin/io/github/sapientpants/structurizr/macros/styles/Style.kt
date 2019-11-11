package io.github.sapientpants.structurizr.macros.styles

import com.structurizr.view.ViewSet

interface Style {
    fun applyToViews(views: ViewSet)
}
