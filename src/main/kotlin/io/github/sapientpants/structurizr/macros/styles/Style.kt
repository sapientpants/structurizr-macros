package io.github.sapientpants.structurizr.macros.styles

import com.structurizr.view.Shape
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

public abstract class Style {
    public val BLACK: String = "#000000"
    public val WHITE: String = "#ffffff"

    public val SYSTEM_OF_INTEREST_BACKGROUND: String = "#0082ff"
    public val CONTAINER_BACKGROUND: String = "#1fa2ff"
    public val COMPONENT_BACKGROUND: String = "#8ac8ff"
    public val DATABASE_BACKGROUND: String = CONTAINER_BACKGROUND
    public val SPRING_MVC_CONTROLLER_BACKGROUND: String = COMPONENT_BACKGROUND
    public val SPRING_SERVICE_BACKGROUND: String = "#b9ddff"
    public val SPRING_REPOSITORY_BACKGROUND: String = "#e2f2ff"

    public abstract fun applyToViews(views: ViewSet)

    protected fun applyBaseStyles(views: ViewSet) {
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
