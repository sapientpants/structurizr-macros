package io.github.sapientpants.structurizr.macros.styles

import com.structurizr.view.Shape
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

class StructurizrStyle : Style() {
    override fun applyToViews(views: ViewSet) {
        val styles = views.configuration.styles

        styles.clearElementStyles()
        styles.clearRelationshipStyles()

        styles.addElementStyle(Tags.ELEMENT)
            .color(WHITE)
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM)
            .color(BLACK)
        styles.addElementStyle(Tags.SYSTEM_OF_INTEREST)
            .background(SYSTEM_OF_INTEREST_BACKGROUND)
            .color(WHITE)
        styles.addElementStyle(Tags.CONTAINER)
            .background(CONTAINER_BACKGROUND)
        styles.addElementStyle(Tags.COMPONENT)
            .background(COMPONENT_BACKGROUND)
            .color(BLACK)
        styles.addElementStyle(Tags.PERSON)
            .background(PERSON_BACKGROUND)
            .shape(Shape.Person)
            .fontSize(22)

        styles.addElementStyle(Tags.DATABASE)
            .background(DATABASE_BACKGROUND)
            .shape(Shape.Cylinder)
        styles.addElementStyle(Tags.MOBILE_DEVICE_PORTRAIT)
            .shape(Shape.MobileDevicePortrait)
        styles.addElementStyle(Tags.WEB_BROWSER)
            .shape(Shape.WebBrowser)

        styles.addElementStyle(Tags.SPRING_MVC_CONTROLLER)
            .background(SPRING_MVC_CONTROLLER_BACKGROUND)
            .color(BLACK)
        styles.addElementStyle(Tags.SPRING_REPOSITORY)
            .background(SPRING_REPOSITORY_BACKGROUND)
            .color(BLACK)
        styles.addElementStyle(Tags.SPRING_SERVICE)
            .background(SPRING_SERVICE_BACKGROUND)
            .color(BLACK)
    }
}
