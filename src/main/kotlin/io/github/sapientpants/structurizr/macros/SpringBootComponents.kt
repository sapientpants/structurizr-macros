package io.github.sapientpants.structurizr.macros

import com.structurizr.analysis.ComponentFinder
import com.structurizr.analysis.ReferencedTypesSupportingTypesStrategy
import com.structurizr.analysis.SourceCodeComponentFinderStrategy
import com.structurizr.analysis.SpringComponentFinderStrategy
import com.structurizr.model.Component
import com.structurizr.model.Container
import java.io.File


object SpringBootComponents {
    const val SPRING_COMPONENT_TAG = "Spring Component"
    const val SPRING_MVC_CONTROLLER_TAG = "Spring MVC Controller"
    const val SPRING_SERVICE_TAG = "Spring Service"
    const val SPRING_REPOSITORY_TAG = "Spring Repository"

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

    private fun addTagsToComponent(component: Component) {
        when (component.technology) {
            SpringComponentFinderStrategy.SPRING_COMPONENT -> component.addTags(SPRING_COMPONENT_TAG)
            SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER -> component.addTags(SPRING_MVC_CONTROLLER_TAG)
            SpringComponentFinderStrategy.SPRING_SERVICE -> component.addTags(SPRING_SERVICE_TAG)
            SpringComponentFinderStrategy.SPRING_REPOSITORY -> component.addTags(SPRING_REPOSITORY_TAG)
        }
    }
}