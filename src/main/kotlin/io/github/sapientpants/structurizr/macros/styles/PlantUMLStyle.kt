package io.github.sapientpants.structurizr.macros.styles

import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

class PlantUMLStyle : Style() {
    override fun applyToViews(views: ViewSet) {
        super.applyBaseStyles(views)

        val styles = views.configuration.styles
        styles.addElementStyle(Tags.PERSON)
            .background(WHITE)
    }
}
