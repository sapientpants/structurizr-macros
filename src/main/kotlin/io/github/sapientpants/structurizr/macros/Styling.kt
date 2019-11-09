package io.github.sapientpants.structurizr.macros

import com.structurizr.view.Shape
import com.structurizr.view.ViewSet

/**
 * Macros for styling diagrams.
 */
object Styling {
    private const val BLACK = "#000000"
    private const val WHITE = "#ffffff"

    private const val PERSON_BACKGROUND = "#ee8002"
    private const val SYSTEM_OF_INTEREST_BACKGROUND = "#0082ff"
    private const val CONTAINER_BACKGROUND = "#1fa2ff"
    private const val COMPONENT_BACKGROUND = "#8ac8ff"
    private const val DATABASE_BACKGROUND = CONTAINER_BACKGROUND
    private const val SPRING_MVC_CONTROLLER_BACKGROUND = COMPONENT_BACKGROUND
    private const val SPRING_SERVICE_BACKGROUND = "#b9ddff"
    private const val SPRING_REPOSITORY_BACKGROUND = "#e2f2ff"

    fun applyPlantUMLStyling(views: ViewSet) {
        val styles = views.configuration.styles
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
            .background(WHITE)
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

    /**
     * Apply the styling to the views.
     *
     * @params views the views
     */
    fun applyStructurizrStyle(views: ViewSet) {
        val styles = views.configuration.styles
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
