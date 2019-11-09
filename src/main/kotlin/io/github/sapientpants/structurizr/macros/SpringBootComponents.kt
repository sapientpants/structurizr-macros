package io.github.sapientpants.structurizr.macros

import com.structurizr.analysis.ComponentFinder
import com.structurizr.analysis.ReferencedTypesSupportingTypesStrategy
import com.structurizr.analysis.SourceCodeComponentFinderStrategy
import com.structurizr.analysis.SpringComponentFinderStrategy
import com.structurizr.model.Component
import com.structurizr.model.Container
import java.io.File

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
object SpringBootComponents {
    /**
     * Tag for components created from Spring's @Component annotation.
     *
     * @see org.springframework.stereotype.Component
     */
    const val SPRING_COMPONENT_TAG = "Spring Component"

    /**
     * Tag for components created from Spring's @Controller annotation.
     *
     * @see org.springframework.stereotype.Controller
     */
    const val SPRING_MVC_CONTROLLER_TAG = "Spring MVC Controller"

    /**
     * Tag for components created from Spring's @Service annotation.
     *
     * @see org.springframework.stereotype.Service
     */
    const val SPRING_SERVICE_TAG = "Spring Service"

    /**
     * Tag for components created from Spring's @Repository annotation.
     *
     * @see org.springframework.stereotype.Repository
     */
    const val SPRING_REPOSITORY_TAG = "Spring Repository"

    /**
     * Finds Spring components and adds them to the specified container.
     *
     * @params container the container to which discovered components should be added
     * @params packageName the base package name to used when scanning for components
     * @params srcPath the path to the source code to scan
     * @see com.structurizr.analysis.SpringComponentFinderStrategy
     * @see org.springframework.stereotype.Component
     * @see org.springframework.stereotype.Controller
     * @see org.springframework.stereotype.Repository
     * @see org.springframework.stereotype.Service
     */
    fun addToContainer(container: Container, packageName: String, srcPath: String) {
        val springComponentFinderStrategy =
            SpringComponentFinderStrategy(ReferencedTypesSupportingTypesStrategy(false))
        springComponentFinderStrategy.setIncludePublicTypesOnly(false)

        val componentFinder = ComponentFinder(
            container,
            packageName,
            springComponentFinderStrategy,
            SourceCodeComponentFinderStrategy(File(srcPath), 150)
        )
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
            SpringComponentFinderStrategy.SPRING_COMPONENT -> component.addTags(SPRING_COMPONENT_TAG)
            SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER -> component.addTags(SPRING_MVC_CONTROLLER_TAG)
            SpringComponentFinderStrategy.SPRING_SERVICE -> component.addTags(SPRING_SERVICE_TAG)
            SpringComponentFinderStrategy.SPRING_REPOSITORY -> component.addTags(SPRING_REPOSITORY_TAG)
        }
    }
}
