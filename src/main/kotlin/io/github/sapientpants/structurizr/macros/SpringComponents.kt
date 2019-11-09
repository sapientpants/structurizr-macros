package io.github.sapientpants.structurizr.macros

import com.structurizr.analysis.ComponentFinder
import com.structurizr.analysis.ReferencedTypesSupportingTypesStrategy
import com.structurizr.analysis.SourceCodeComponentFinderStrategy
import com.structurizr.analysis.SpringComponentFinderStrategy
import com.structurizr.model.Component
import com.structurizr.model.Container
import java.io.File
import java.net.URLClassLoader

/**
 * Macros to add Components to a Container using Spring annotations.
 *
 * @see com.structurizr.model.Component
 * @see com.structurizr.model.Container
 * @see com.structurizr.analysis.SpringComponentFinderStrategy
 * @see org.springframework.stereotype.Component
 * @see org.springframework.stereotype.Controller
 * @see org.springframework.stereotype.Repository
 * @see org.springframework.stereotype.Service
 */
object SpringComponents {
    /**
     * Finds Spring components and adds them to the specified container.
     *
     * @params container the container to which discovered components should be added
     * @params packageName the base package name to used when scanning for components
     * @params srcPath the path to the source code to scan
     * @params exclusions regexes of types to be excluded during the component finding process
     * @params urlClassLoader classloader to load classes from instead of the system classloader
     * @see com.structurizr.analysis.SpringComponentFinderStrategy
     * @see org.springframework.stereotype.Component
     * @see org.springframework.stereotype.Controller
     * @see org.springframework.stereotype.Repository
     * @see org.springframework.stereotype.Service
     */
    fun addToContainer(
        container: Container,
        packageName: String,
        srcPath: String,
        vararg exclusions: String = emptyArray(),
        urlClassLoader: URLClassLoader? = null
    ) {
        val springComponentFinderStrategy =
            SpringComponentFinderStrategy(ReferencedTypesSupportingTypesStrategy(false))
        springComponentFinderStrategy.setIncludePublicTypesOnly(false)

        val componentFinder = ComponentFinder(
            container,
            packageName,
            springComponentFinderStrategy,
            SourceCodeComponentFinderStrategy(File(srcPath), 150)
        )

        if (!exclusions.isNullOrEmpty()) {
            componentFinder.exclude(*exclusions)
        }

        componentFinder.urlClassLoader = urlClassLoader

        val components = componentFinder.findComponents()

        components.forEach { c -> addTagsToComponent(c) }
    }

    /**
     * Adds the relevant tags to the discovered component.
     *
     * @see SPRING_COMPONENT_TAG
     * @see SPRING_MVC_CONTROLLER_TAG
     * @see SPRING_SERVICE_TAG
     * @see SPRING_REPOSITORY_TAG
     */
    private fun addTagsToComponent(component: Component) {
        when (component.technology) {
            SpringComponentFinderStrategy.SPRING_COMPONENT -> component.addTags(Tags.SPRING_COMPONENT)
            SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER -> component.addTags(Tags.SPRING_MVC_CONTROLLER)
            SpringComponentFinderStrategy.SPRING_SERVICE -> component.addTags(Tags.SPRING_SERVICE)
            SpringComponentFinderStrategy.SPRING_REPOSITORY -> component.addTags(Tags.SPRING_REPOSITORY)
        }
    }
}
