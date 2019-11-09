package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Tags
import com.structurizr.view.Shape
import com.structurizr.view.ViewSet

/**
 * Macros for styling diagrams.
 */
object Styling {
    /**
     * Tag to mark an element as a database.
     */
    const val DATABASE_TAG = "Database"

    /**
     * Tag to mark an element as a mobile device that should be rendered in portrait orientation.
     */
    const val MOBILE_DEVICE_PORTRAIT_TAG = "Mobile Device Portrait"

    /**
     * Tag to mark a SoftwareSystem as the system of interest.
     *
     * @see com.structurizr.model.SoftwareSystem
     */
    const val SYSTEM_OF_INTEREST_TAG = "System of Interest"

    /**
     * Tag to mark an element as a web browser.
     */
    const val WEB_BROWSER_TAG = "Web Browser"

    private const val BLACK = "#000000"
    private const val WHITE = "#FFFFFF"

    fun applyPlantUMLStyling(views: ViewSet) {
        val styles = views.configuration.styles
        styles.addElementStyle(Tags.ELEMENT)
            .color(WHITE)
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM)
            .color(BLACK)
        styles.addElementStyle(SYSTEM_OF_INTEREST_TAG)
            .background("#1168bd")
            .color(WHITE)
        styles.addElementStyle(Tags.CONTAINER)
            .background("#438dd5")
        styles.addElementStyle(Tags.COMPONENT)
            .background("#85bbf0")
            .color(BLACK)
        styles.addElementStyle(Tags.PERSON)
            .background(WHITE)
            .shape(Shape.Person)
            .fontSize(22)

        styles.addElementStyle(DATABASE_TAG)
            .shape(Shape.Cylinder)
        styles.addElementStyle(MOBILE_DEVICE_PORTRAIT_TAG)
            .shape(Shape.MobileDevicePortrait)
        styles.addElementStyle(WEB_BROWSER_TAG)
            .shape(Shape.WebBrowser)
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
        styles.addElementStyle(SYSTEM_OF_INTEREST_TAG)
            .background("#1168bd")
            .color(WHITE)
        styles.addElementStyle(Tags.CONTAINER)
            .background("#438dd5")
        styles.addElementStyle(Tags.COMPONENT)
            .background("#85bbf0")
            .color(BLACK)
        styles.addElementStyle(Tags.PERSON)
            .background("#08427b")
            .shape(Shape.Person)
            .fontSize(22)

        styles.addElementStyle(DATABASE_TAG)
            .shape(Shape.Cylinder)
        styles.addElementStyle(MOBILE_DEVICE_PORTRAIT_TAG)
            .shape(Shape.MobileDevicePortrait)
        styles.addElementStyle(WEB_BROWSER_TAG)
            .shape(Shape.WebBrowser)
    }
}
