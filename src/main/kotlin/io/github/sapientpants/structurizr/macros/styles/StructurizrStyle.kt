package io.github.sapientpants.structurizr.macros.styles

import com.structurizr.view.Shape
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

class StructurizrStyle : Style() {
    private val PERSON_BACKGROUND = "#f48f05"

    override fun applyToViews(views: ViewSet) {
        super.applyBaseStyles(views)

        val styles = views.configuration.styles
        styles.addElementStyle(Tags.PERSON)
            .background(PERSON_BACKGROUND)
            .shape(Shape.Person)
            .fontSize(22)
    }
}
