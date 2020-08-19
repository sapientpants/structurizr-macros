package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Element

public object Tags {
    public const val ASYNCHRONOUS: String = com.structurizr.model.Tags.ASYNCHRONOUS

    public const val COMPONENT: String = com.structurizr.model.Tags.COMPONENT

    public const val CONTAINER: String = com.structurizr.model.Tags.CONTAINER

    /**
     * Tag to mark an element as a database.
     */
    public const val DATABASE: String = "Database"

    public const val ELEMENT: String = com.structurizr.model.Tags.ELEMENT

    public const val EXTERNAL: String = "External"

    public const val INTERNAL: String = "Internal"

    public const val PERSON: String = com.structurizr.model.Tags.PERSON

    /**
     * Tag to mark an element as a mobile device that should be rendered in portrait orientation.
     */
    public const val MOBILE_DEVICE_PORTRAIT: String = "Mobile Device Portrait"

    public const val RELATIONSHIP: String = com.structurizr.model.Tags.RELATIONSHIP

    public const val SOFTWARE_SYSTEM: String = com.structurizr.model.Tags.SOFTWARE_SYSTEM

    /**
     * Tag for components created from Spring's @Component annotation.
     *
     * @see org.springframework.stereotype.Component
     */
    public const val SPRING_COMPONENT: String = "Spring Component"

    /**
     * Tag for components created from Spring's @Controller annotation.
     *
     * @see org.springframework.stereotype.Controller
     */
    public const val SPRING_MVC_CONTROLLER: String = "Spring MVC Controller"

    /**
     * Tag for components created from Spring's @Service annotation.
     *
     * @see org.springframework.stereotype.Service
     */
    public const val SPRING_SERVICE: String = "Spring Service"

    /**
     * Tag for components created from Spring's @Repository annotation.
     *
     * @see org.springframework.stereotype.Repository
     */
    public const val SPRING_REPOSITORY: String = "Spring Repository"

    public const val SYNCHRONOUS: String = com.structurizr.model.Tags.SYNCHRONOUS

    /**
     * Tag to mark a SoftwareSystem as the system of interest.
     *
     * @see com.structurizr.model.SoftwareSystem
     */
    public const val SYSTEM_OF_INTEREST: String = "System of Interest"

    /**
     * Tag to mark an element as a web browser.
     */
    public const val WEB_BROWSER: String = "Web Browser"

    /**
     * Filters elements with all of the supplied tags. When tags is empty, then all the
     * input elements are returned.
     *
     * @params elements the elements to filter
     * @params tags the tags to use for filtering
     * @return those elements tagged with all of the supplied tags
     */
    public fun <T : Element> filter(elements: Set<T>, tags: Set<String>): Set<T> {
        return elements.filter { element ->
            tags.all { tag -> element.hasTag(tag) }
        }.toSet()
    }
}
