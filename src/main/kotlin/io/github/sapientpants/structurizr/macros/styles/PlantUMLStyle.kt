package io.github.sapientpants.structurizr.macros.styles

import com.structurizr.view.Shape
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

class PlantUMLStyle : Style {
    override fun applyToViews(views: ViewSet) {
        val styles = views.configuration.styles

        styles.clearElementStyles()
        styles.clearRelationshipStyles()

        styles.addElementStyle(Tags.ELEMENT)
            .color(Defaults.WHITE)
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM)
            .color(Defaults.BLACK)
        styles.addElementStyle(Tags.SYSTEM_OF_INTEREST)
            .background(Defaults.SYSTEM_OF_INTEREST_BACKGROUND)
            .color(Defaults.WHITE)
        styles.addElementStyle(Tags.CONTAINER)
            .background(Defaults.CONTAINER_BACKGROUND)
        styles.addElementStyle(Tags.COMPONENT)
            .background(Defaults.COMPONENT_BACKGROUND)
            .color(Defaults.BLACK)
        styles.addElementStyle(Tags.PERSON)
            .background(Defaults.WHITE)
            .shape(Shape.Person)
            .fontSize(22)

        styles.addElementStyle(Tags.DATABASE)
            .background(Defaults.DATABASE_BACKGROUND)
            .shape(Shape.Cylinder)
        styles.addElementStyle(Tags.MOBILE_DEVICE_PORTRAIT)
            .shape(Shape.MobileDevicePortrait)
        styles.addElementStyle(Tags.WEB_BROWSER)
            .shape(Shape.WebBrowser)

        styles.addElementStyle(Tags.SPRING_MVC_CONTROLLER)
            .background(Defaults.SPRING_MVC_CONTROLLER_BACKGROUND)
            .color(Defaults.BLACK)
        styles.addElementStyle(Tags.SPRING_REPOSITORY)
            .background(Defaults.SPRING_REPOSITORY_BACKGROUND)
            .color(Defaults.BLACK)
        styles.addElementStyle(Tags.SPRING_SERVICE)
            .background(Defaults.SPRING_SERVICE_BACKGROUND)
            .color(Defaults.BLACK)
    }
}
