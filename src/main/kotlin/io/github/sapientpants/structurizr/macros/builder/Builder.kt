package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.Workspace
import com.structurizr.model.Model
import com.structurizr.model.SoftwareSystem
import io.github.sapientpants.structurizr.macros.Tags
import io.github.sapientpants.structurizr.macros.Utils
import io.github.sapientpants.structurizr.macros.styles.Style
import io.github.sapientpants.structurizr.macros.views.ComponentViews
import io.github.sapientpants.structurizr.macros.views.ContainerView
import io.github.sapientpants.structurizr.macros.views.DeploymentViews
import io.github.sapientpants.structurizr.macros.views.SystemContextView
import io.github.sapientpants.structurizr.macros.views.SystemLandscapeView

object Builder {
    fun addImplicitRelationshipsToModel(model: Model, addImplicitRelationships: Boolean) {
        if (addImplicitRelationships) {
            model.addImplicitRelationships()
        }
    }

    fun finalizeModelAndAddViewsToWorkspace(
        workspace: Workspace,
        addImplicitRelationships: Boolean,
        style: Style
    ) {
        val model = workspace.model
        addImplicitRelationshipsToModel(model, addImplicitRelationships)
        addViewsToWorkspace(workspace, style)
    }

    fun addViewsToWorkspace(workspace: Workspace, style: Style) {
        val model = workspace.model
        val views = workspace.views

        val systemsOfInterest = systemsOfInterest(model)

        SystemLandscapeView.addToViews(model, views)

        systemsOfInterest.forEach { systemOfInterest ->
            SystemContextView.addToViews(systemOfInterest, views)

            ContainerView.addToViews(systemOfInterest, views)

            ComponentViews.addToViews(systemOfInterest.containers, views)

            DeploymentViews.addToViews(systemOfInterest, views)
        }

        // Apply the style
        style.applyToViews(views)
    }

    fun systemsOfInterest(model: Model): Set<SoftwareSystem> {
        check(model.softwareSystems.isNotEmpty()) { "No software systems in model" }

        val taggedSystemsOfInterest = Utils.filter(
            model.softwareSystems,
            setOf(Tags.SYSTEM_OF_INTEREST)
        )

        if (!taggedSystemsOfInterest.isEmpty()) {
            return taggedSystemsOfInterest
        }

        val softwareSystem = model.softwareSystems.first()

        // Ensure the software system we take as the system of interest is
        // tagged as such
        softwareSystem.addTags(Tags.SYSTEM_OF_INTEREST)

        return setOf(softwareSystem)
    }
}
