package io.github.sapientpants.structurizr.macros.styles

import com.structurizr.view.Shape
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

public class PlantUMLStyle : Style() {
    override fun applyToViews(views: ViewSet) {
        super.applyBaseStyles(views)

        val styles = views.configuration.styles
        styles.addElementStyle(Tags.PERSON)
            .background(WHITE)
            .shape(Shape.Person)
            .fontSize(22)
    }
}
