package io.github.sapientpants.structurizr.macros

object Tags {
    const val COMPONENT = com.structurizr.model.Tags.COMPONENT

    const val CONTAINER = com.structurizr.model.Tags.CONTAINER

    /**
     * Tag to mark an element as a database.
     */
    const val DATABASE = "Database"

    const val ELEMENT = com.structurizr.model.Tags.ELEMENT

    const val PERSON = com.structurizr.model.Tags.PERSON

    /**
     * Tag to mark an element as a mobile device that should be rendered in portrait orientation.
     */
    const val MOBILE_DEVICE_PORTRAIT = "Mobile Device Portrait"

    const val SOFTWARE_SYSTEM = com.structurizr.model.Tags.SOFTWARE_SYSTEM

    /**
     * Tag for components created from Spring's @Component annotation.
     *
     * @see org.springframework.stereotype.Component
     */
    const val SPRING_COMPONENT = "Spring Component"

    /**
     * Tag for components created from Spring's @Controller annotation.
     *
     * @see org.springframework.stereotype.Controller
     */
    const val SPRING_MVC_CONTROLLER = "Spring MVC Controller"

    /**
     * Tag for components created from Spring's @Service annotation.
     *
     * @see org.springframework.stereotype.Service
     */
    const val SPRING_SERVICE = "Spring Service"

    /**
     * Tag for components created from Spring's @Repository annotation.
     *
     * @see org.springframework.stereotype.Repository
     */
    const val SPRING_REPOSITORY = "Spring Repository"

    /**
     * Tag to mark a SoftwareSystem as the system of interest.
     *
     * @see com.structurizr.model.SoftwareSystem
     */
    const val SYSTEM_OF_INTEREST = "System of Interest"

    /**
     * Tag to mark an element as a web browser.
     */
    const val WEB_BROWSER = "Web Browser"
}